/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import fr.inrialpes.exmo.align.impl.method.NameEqAlignment;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentProcess;


/**
 *
 * @author MC
 */
public class Aligner {

    public static void main(String[] args) {
        try {
            generateAlign();
        } catch (URISyntaxException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlignmentException ex) {
            Logger.getLogger(Aligner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void generateAlign() throws URISyntaxException, AlignmentException {
        URI onto1 = new URI("http://oaei.ontologymatching.org/tests/101/onto.rdf");
        URI onto2 = new URI("http://oaei.ontologymatching.org/tests/304/onto.rdf");
        AlignmentProcess alignment = new NameEqAlignment();
        alignment.init(onto1, onto2);
        alignment.align(null, new Properties());
        System.out.println("Num corresp. générées : " + alignment.nbCells());
    }

}
