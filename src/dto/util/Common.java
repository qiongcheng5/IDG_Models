package dto.util;

/**
 * @author Qiong Cheng
 * 
 * Constants or common methods are to define here.
 */

public final class Common {
	public static boolean isDebug=false;
        
        // DB-associated configuration items
        public static String DBServiceItemInConfiguration = "Service";
        public static String Monarch_DTO_DataScheme_ItemName_in_Configuration = "monarch_dto_service";
        public static String DTO_DataScheme_ItemName_in_Configuration = "dto_service";
				
        public static int Gene_Phenotype_Relationship = 1;
        
	public static Double logitFunction(double prob){
            return new Double(Math.log(prob)-Math.log(1-prob));
	}
}
