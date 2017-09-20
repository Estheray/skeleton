package dao;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    //return true if tagreceipt combo already exists
   public boolean tagReceiptCombo(String tagName, Integer receiptID){
        return(dsl.selectFrom(TAGS)
                .where(TAGS.TAGNAME.eq(tagName))
                .and(TAGS.RECEIPTID.eq(receiptID)).fetch().size() > 0);

    }
    public void deleteRecord(String tagName, Integer receiptID){
       dsl.deleteFrom(TAGS)
               .where(TAGS.TAGNAME.eq(tagName))
               .and(TAGS.RECEIPTID.eq(receiptID)).execute();
    }

    public Integer insert(String tagName, int receiptID) {
        //System.out.println("insert tag");
        if (dsl.select().from(RECEIPTS)
                .where(RECEIPTS.ID.eq(receiptID))
                .fetch().size() > 0) {
            //System.out.println("receipt exists");
            TagsRecord tagsRecord = dsl
                    .insertInto(TAGS, TAGS.TAGNAME, TAGS.RECEIPTID)
                    .values(tagName, receiptID)
                    .returning(TAGS.TAGID)
                    .fetchOne();
            //System.out.println("tag created");
            checkState(tagsRecord != null && tagsRecord.getTagid() != null, "Insert failed");

            return tagsRecord.getTagid();
        }
        //System.out.println("Receipt doesnt exists");
        return null;
    }

    public List<TagsRecord> getAllTags() {
        return dsl.selectFrom(TAGS).fetch();
        //return dsl.selectFrom(TAGS).fetch();
    }

    public List<TagsRecord> getTagsByReceipt(Integer id){
        return dsl.selectFrom(TAGS).where(TAGS.RECEIPTID.eq(id)).fetch();
    }

    public List<ReceiptsRecord> getAllReceiptsByTags(String tagName) {
        return dsl.select()
                  .from(RECEIPTS)
                  .join(TAGS)
                  .on(RECEIPTS.ID.eq(TAGS.RECEIPTID).and(TAGS.TAGNAME.equal(tagName)))
                  .fetchInto(RECEIPTS);
        //return dsl.selectFrom(TAGS).fetch();
    }
}
