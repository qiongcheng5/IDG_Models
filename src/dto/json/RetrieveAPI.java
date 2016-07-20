/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.json;

//import static dto.json.DownloadFasta.accessionNumber;
//import static dto.json.DownloadFasta.fastaFileName;
import dto.GenePhenotypeRelInMonarch;
import dto.db.DBHandler4Monarch_DTO;
import dto.util.Common;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Vector;
import dto.wwwapi.GetURL;
import static dto.wwwapi.GetURL.createAFile;
import java.util.LinkedList;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * The class provides a general operation of retrieving data from API access
 * 
 * @author Qiong Cheng
 */
public class RetrieveAPI {
    
    /*
    API example: https://monarchinitiative.org/gene/NCBIGene:2629
    
    https://solr.monarchinitiative.org/solr/golr/select?defType=edismax&qt=standard&indent=on&wt=csv&rows=100000&start=0&fl=subject,subject_label,subject_taxon,subject_taxon_label,relation,relation_label,object,object_label,evidence,evidence_label,source,is_defined_by,qualifier&facet=true&facet.mincount=1&facet.sort=count&json.nl=arrarr&facet.limit=25&facet.method=enum&csv.encapsulator=&csv.separator=%09&csv.header=true&csv.mv.separator=%7C&fq=subject_closure:%22NCBIGene:2629%22&fq=object_category:%22phenotype%22&facet.field=subject_taxon_label&q=*:*
    
    */
    
    /** Creates a new instance of DownloadFasta */
    public RetrieveAPI() {
    	
        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        
        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            SecureRandom x = new java.security.SecureRandom();
            sc.init(null, trustAllCerts, x);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
        
    }
    
    public void getAllGenePhenotypeAssocitions(){
        // database access
        
        
        // Iteratively retrieving gene-phenotype associations
        
    }
    
    public void getAllGenePhenotypeAssocitionsOfDTOMonarchOverlap(DBHandler4Monarch_DTO dbhandler) throws IOException{
        try{
            // database access
            LinkedList<Long> geneIDs = dbhandler.getCommonGeneIDsOfDTOMonarch();

            // Iteratively retrieving gene-phenotype associations
            int count = 0;
            for (Long geneID : geneIDs){
                System.out.println("[RetrieveAPI:getAllGenePhenotypeAssocitionsOfDTOMonarchOverlap] " + count + " : " + geneID.longValue());
                retrieve(Common.Gene_Phenotype_Relationship, dbhandler, geneID.longValue());
                count++;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    /** This method does the actual GET   */
    public void retrieveRelationship(String theUrl, DBHandler4Monarch_DTO dbhandler, int relType) throws IOException
    {
        try {
            URL gotoUrl = new URL(theUrl);
            InputStreamReader isr = new InputStreamReader(gotoUrl.openStream());
            BufferedReader in = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            String inputLine;
            boolean isFirst = true;
            
            //grab the contents at the URL
            while ((inputLine = in.readLine()) != null){
                if (isFirst){
                    isFirst = false;
                    continue;
                }
                String items[] = inputLine.split("\t");
                switch (relType){
                    case 1: 
                        GenePhenotypeRelInMonarch relobj = new GenePhenotypeRelInMonarch(
                                items[0], items[1], items[2], items[3],
                                items[4], items[5], items[6], items[7],
                                items[8], items[9], items[10], items[11],
                                items[12]);
                        dbhandler.insertGenePhenotypeAssociation(relobj);
                        break;
                    case 2://other cases ......
                    default:
                }
            }
           
        }
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
        catch (IOException ioe) {
            throw ioe;
        }
    }
    
    public void retrieve(int relType, DBHandler4Monarch_DTO dbhandler, long geneID) throws Exception{
        
        String URL="https://solr.monarchinitiative.org/solr/golr/select?defType=edismax&qt=standard&indent=on&wt=csv&rows=100000&start=0&fl=subject,subject_label,subject_taxon,subject_taxon_label,relation,relation_label,object,object_label,evidence,evidence_label,source,is_defined_by,qualifier&facet=true&facet.mincount=1&facet.sort=count&json.nl=arrarr&facet.limit=25&facet.method=enum&csv.encapsulator=&csv.separator=%09&csv.header=true&csv.mv.separator=%7C&fq=subject_closure:%22NCBIGene:" + geneID + "%22&fq=object_category:%22phenotype%22&facet.field=subject_taxon_label&q=*:*";
        
        retrieveRelationship(URL, dbhandler , relType);

    }

}

