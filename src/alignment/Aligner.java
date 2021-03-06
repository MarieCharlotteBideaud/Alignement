/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import fr.inrialpes.exmo.align.impl.method.EditDistNameAlignment;
import fr.inrialpes.exmo.align.impl.method.NameAndPropertyAlignment;
import fr.inrialpes.exmo.align.impl.method.NameEqAlignment;
import fr.inrialpes.exmo.align.impl.method.SMOANameAlignment;
import fr.inrialpes.exmo.ontowrap.OntowrapException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author MC
 */
public class Aligner {

    public static void main(String[] args) {
        try {
            generateAlign(0);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlignmentException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // 0 pour l'initial
    // 1 pour le NewMatcher
    // 2 pour LogMap
    public static void generateAlign(int matcher) throws URISyntaxException, AlignmentException, FileNotFoundException, UnsupportedEncodingException {
        // URI onto1 = new URI("http://oaei.ontologymatching.org/tests/101/onto.rdf");
        // Cinema : 
        URI onto1 = new URI("http://www.irit.fr/recherches/MELODI/ontologies/FilmographieV1.owl");
        // URI onto2 = new URI("http://oaei.ontologymatching.org/tests/304/onto.rdf");
        // DBPedia : 
        URI onto2 = new URI("file:///Users/MC/Documents/5A/RepConn/Alignement/dbpedia_2014.owl");
        if (matcher == 0) {
            AlignmentProcess alignment = new SMOANameAlignment();
            alignment.init(onto1, onto2);
            alignment.align(null, new Properties());
            System.out.println("Num corresp. générées : " + alignment.nbCells());
            // Ecriture dans un fichier rdf 
            Renderer.render(alignment, "alignInitialMatcher.rdf");
            // Evaluation
            Evaluator.evaluate(alignment);
        } else if (matcher == 1) {
            NewMatcher nm = new NewMatcher();
            try {
                nm.init(onto1, onto2);
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OntowrapException ex) {
                Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
            }
            // 3ème param est à 0 si on utilise le new matcher de base, 1 si on utilise la distance de Levenshtein
            nm.align(null, new Properties(), 1);
            System.out.println("Num corresp. générées : " + nm.nbCells());
            // Ecriture dans un fichier rdf 
            Renderer.render(nm, "alignNewMatcher.rdf");
            // Evaluation
            Evaluator.evaluate(nm);
        } else if (matcher == 2) {
            LogMap lm = new LogMap();
            try {

                lm.init(onto1, onto2);

            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OntowrapException ex) {
                Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
            }
            lm.align();
            // Ecriture dans un fichier rdf 
            Renderer.render(lm, "alignLogMap.rdf");
            // Evaluation
            Evaluator.evaluate(lm);
        }

    }

}
