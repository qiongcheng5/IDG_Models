/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monarchapp;

import dto.db.DBHandler4Monarch_DTO;
import dto.json.RetrieveAPI;
import dto.util.Common;
import dto.util.ConfigFile;
import dto.util.DBSource;
import java.sql.Connection;
import java.util.LinkedList;

/**
 *
 * @author Qiong Cheng
 */
public class MonarchApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        	 
        		System.setProperty("user.dir", ConfigFile.getBaseDir()); 
        		
            //Connection conn = DBSource.getInstance().getConnection("monarch_dto");
            System.out.println("--1---" + System.getProperty("user.dir") + " " + ConfigFile.getProperty(Common.Monarch_DTO_DataScheme_ItemName_in_Configuration));
            Connection conn = DBSource.getInstance().getConnection(ConfigFile.getProperty(Common.Monarch_DTO_DataScheme_ItemName_in_Configuration));
            DBHandler4Monarch_DTO db= new DBHandler4Monarch_DTO(conn);
            System.out.println("--2---");
            
            db.dropTable4GenePhenotypeAssociations();
            db.creatTable4GenePhenotypeAssociations();
            System.out.println("--3---");
            
            RetrieveAPI retrieveapi = new RetrieveAPI();
            //retrieveapi.retrieve(Common.Gene_Phenotype_Relationship, db, 774);
            retrieveapi.getAllGenePhenotypeAssocitionsOfDTOMonarchOverlap(db);
            System.out.println("--4---");
            
            LinkedList list1 = db.getDTOProteins();
            System.out.println(list1.size());
            
            LinkedList list2 = db.getTargetsFromDisGeNET();
            System.out.println(list2.size());
            
            db.release();
            DBSource.getInstance().release();
    	}catch(Exception ex){
            ex.printStackTrace();
    	}
    }
    
}
