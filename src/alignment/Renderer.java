/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alignment;

import fr.inrialpes.exmo.align.impl.renderer.RDFRendererVisitor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owl.align.AlignmentVisitor;

/**
 *
 * @author MC
 */
public class Renderer {

    public static void render(Alignment alignment) throws FileNotFoundException, UnsupportedEncodingException, AlignmentException {
        PrintWriter writer;
        FileOutputStream f = new FileOutputStream(new File("PATH"));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(f,
                "UTF -8")), true);
        AlignmentVisitor renderer = new RDFRendererVisitor(writer);
        alignment.render(renderer);
        writer.flush();
        writer.close();
    }
}
