/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import fr.inrialpes.exmo.align.impl.eval.PRecEvaluator;
import fr.inrialpes.exmo.align.parser.AlignmentParser;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;

/**
 *
 * @author MC
 */
public class Evaluator {

    public static void evaluate(Alignment alignment) throws URISyntaxException, AlignmentException {
        URI reference = new URI("http://oaei.ontologymatching.org/tests/304/refalign.rdf");
        AlignmentParser aparser = new AlignmentParser(0);
        Alignment refalign = aparser.parse(reference);
        PRecEvaluator evaluator = new PRecEvaluator(refalign, alignment);
        evaluator.eval(new Properties());
        System.out.println("Precision : " + evaluator.getPrecision());
        System.out.println("Recall :" + evaluator.getRecall());
        System.out.println("FMeasure :" + evaluator.getFmeasure());
    }
}
