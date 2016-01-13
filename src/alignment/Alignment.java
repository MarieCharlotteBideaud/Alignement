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
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentProcess;


/**
 *
 * @author MC
 */
public class Alignment {

    public static void generateAlign() throws URISyntaxException, AlignmentException {
        URI onto1 = new URI("http://oaei.ontologymatching.org/tests/101/onto.rdf");
        URI onto2 = new URI("http://oaei.ontologymatching.org/tests/304/onto.rdf");
        AlignmentProcess alignment = new NameEqAlignment();
        alignment.init(onto1, onto2);
        alignment.align(null, new Properties());
        System.out.println("Num corresp. générées : " + alignment.nbCells());
    }

}
