/**
 * This class is generated by jOOQ
 */
package generated;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

	/**
	 * The sequence <code>public.system_sequence_ad714dc1_6a36_4d7f_ad9c_626ca9e79b7e</code>
	 */
	public static final Sequence<Long> SYSTEM_SEQUENCE_AD714DC1_6A36_4D7F_AD9C_626CA9E79B7E = new SequenceImpl<Long>("system_sequence_ad714dc1_6a36_4d7f_ad9c_626ca9e79b7e", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT);

	/**
	 * The sequence <code>public.system_sequence_eac50dab_2fda_4f38_a626_50ecaeb42b50</code>
	 */
	public static final Sequence<Long> SYSTEM_SEQUENCE_EAC50DAB_2FDA_4F38_A626_50ECAEB42B50 = new SequenceImpl<Long>("system_sequence_eac50dab_2fda_4f38_a626_50ecaeb42b50", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT);
}
