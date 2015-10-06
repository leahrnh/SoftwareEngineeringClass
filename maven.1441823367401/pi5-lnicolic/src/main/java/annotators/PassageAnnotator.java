package annotators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Passage;

/**
 * Annotates passage lines from the input document
 *
 */
public class PassageAnnotator extends JCasAnnotator_ImplBase {
	
	private Pattern question = Pattern.compile("([0-9]+) ([A-Z]+[0-9]+\\.[0-9]+) (-1|1|2) (.+)");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		// search for questions
		Matcher matcher = question.matcher(docText);
		int pos = 0;
		while (matcher.find(pos)) {
			// found one - create annotation
			Passage passage = new Passage(aJCas);
		    passage.setBegin(matcher.start());
            passage.setEnd(matcher.end());
            //set other parts of question
            passage.setQuestionId(matcher.group(1));
            passage.setSourceDocId(matcher.group(2));
            passage.setLabel(!matcher.group(3).equals("-1"));
            String sentence = cleanSentence(matcher.group(4));
            passage.setSentence(sentence); //TODO more processing to make it pretty
            passage.addToIndexes();
	        pos = matcher.end();
	    }
	}	
	
	/**
	 * Remove any extraneous symbols, noise, etc from a text to prepare it for further processing
	 * @param s a string including symbols, tags, etc
	 * @return the cleaned string
	 */
	private String cleanSentence(String s) {
		Pattern tag = Pattern.compile("<[/?A-Z]+>");
		Matcher m = tag.matcher(s);
		s = m.replaceAll("");
		
		tag = Pattern.compile("&[A-Z][A-Z][A-Z]?;");
		m = tag.matcher(s);
		s = m.replaceAll("");
		
		return s;
	}

}
