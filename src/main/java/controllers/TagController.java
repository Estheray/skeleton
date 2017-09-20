package controllers;

//import api.TagResponse;
import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.Tags;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.DSLContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    //DSLContext dsl;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

 /*   @GET
    public List<TagResponse> getTags() {
        List<TagsRecord> tagRecords = tags.getAllTags();
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }*/

 /*   @POST
    public int createReceipt(@Valid @NotNull CreateReceiptRequest receipt) {
        return receipts.insert(receipt.merchant, receipt.amount);
    }*/
    @Path("/{tag}")
    @PUT
    public void toggleTag(@PathParam("tag") String tagName, int receiptID) {
        //System.out.println("--------------PUT TAG " + tagName + " " + receiptID + "----------------");
        if (tags.tagReceiptCombo(tagName,receiptID)){
            //System.out.println("Tag combo already exists");
            tags.deleteRecord(tagName,receiptID);
            //System.out.println("Receipt "+receiptID+" with tag "+tagName+" exists. Deleting record");
        }
        else {
            //System.out.println("New tag combo");
            tags.insert(tagName, receiptID);
        }

    }
    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getReceiptsByTag(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getAllReceiptsByTags(tagName);

        List<ReceiptResponse> receiptResponses= new ArrayList<ReceiptResponse>();
        for (int i = 0; i<receiptRecords.size();i++) {
            receiptResponses.add(new ReceiptResponse(receiptRecords.get(i), tags.getTagsByReceipt(receiptRecords.get(i).getId()).stream().map(TagsRecord::getTagname).collect(toList())));
        }
        return receiptResponses;
    }

}

/*simple application
        dao using
                schema create table
./gradlew generate
*/

