/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import fr.inrialpes.exmo.align.impl.URIAlignment;
import fr.inrialpes.exmo.ontowrap.OntowrapException;
import java.net.URI;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import uk.ac.ox.krr.logmap2.LogMap2_Matcher;
import uk.ac.ox.krr.logmap2.mappings.objects.MappingObjectStr;

/**
 *
 * @author MC
 */
public class LogMap extends URIAlignment {

    private OWLOntology ontology1;
    private OWLOntology ontology2;

    private OWLOntologyManager man1;
    private OWLOntologyManager man2;

    public void init(URI uri1, URI uri2) throws AlignmentException, OWLOntologyCreationException, OntowrapException {
        super.init(uri1, uri2);
        load(uri1, uri2);
    }

    public void load(URI uri1, URI uri2) throws OWLOntologyCreationException, AlignmentException, OntowrapException {
        man1 = OWLManager.createOWLOntologyManager();
        man2 = OWLManager.createOWLOntologyManager();
        ontology1 = man1.loadOntologyFromOntologyDocument(IRI.create(uri1));
        ontology2 = man2.loadOntologyFromOntologyDocument(IRI.create(uri2));
    }

    public void align() {
        LogMap2_Matcher logmap2 = new LogMap2_Matcher(ontology1, ontology2);
        Set<MappingObjectStr> logmap2_mappings = logmap2.getLogmap2_Mappings();
        for (MappingObjectStr mapp : logmap2.getLogmap2_Mappings()) {
            try {
                addAlignCell(new URI(mapp.getIRIStrEnt1()), new URI(mapp.getIRIStrEnt2()), "=", mapp.getConfidence());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("Num. mappings : " + logmap2_mappings.size());

    }
}
