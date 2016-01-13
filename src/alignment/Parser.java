/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.align;
import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.align;
import fr.inrialpes.exmo.align.parser.AlignmentParser;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;

/**
 *
 * @author MC
 */
public class Parser {

    public void parseRDF(String path) {
        AlignmentParser aparser = new AlignmentParser(0);
// Attention au PATH => file:/ => Example : file:/home/cassia/web-sem/fichier -align.rdf 
        Alignment al = null;
        try {
            al = aparser.parse(new URI(path));

        } catch (AlignmentException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(al.nbCells());
    }
}
