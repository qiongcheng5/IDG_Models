/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Qiong Cheng
 */
public class GenePhenotypeRelInMonarch {
    
    public String subject, subject_label,subject_taxon, subject_taxon_label, relation, relation_label, object, object_label, evidence, evidence_label, source, is_defined_by, qualifier;
    
    public GenePhenotypeRelInMonarch(String subject, 
            String subject_label,
            String subject_taxon,
            String subject_taxon_label,
            String relation,
            String relation_label,
            String object,
            String object_label,
            String evidence,
            String evidence_label,
            String source,
            String is_defined_by,
            String qualifier){
        
        this.subject = subject;
        this.subject_label = subject_label;
        this.subject_taxon = subject_taxon;
        this.subject_taxon_label = subject_taxon_label;
        this.relation = relation;
        this.relation_label = relation_label;
        this.object = object;
        this.object_label = object_label;
        this.evidence = evidence;
        this.evidence_label = evidence_label;
        this.source = source;
        this.is_defined_by = is_defined_by;
        this.qualifier = qualifier;
    }
}
