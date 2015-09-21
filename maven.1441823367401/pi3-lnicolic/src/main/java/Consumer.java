import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import type.Answer;
import type.InputDocument;
import type.Ngram;
import type.Question;


public class Consumer extends CasConsumer_ImplBase {
	
	private int mDocNum;
	private File mOutputDir;
	
	public void initialize() throws ResourceInitializationException {
		mDocNum = 0;
		mOutputDir = new File("src/main/resources/outputData");
		if (!mOutputDir.exists()) {
		      mOutputDir.mkdirs();
		}
	}

	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		  //String modelFileName = null;

		  JCas jcas;
		  try {
		      jcas = aCAS.getJCas();
		  } catch (CASException e) {
		      throw new ResourceProcessException(e);
		  }
		  
		  FSIterator docIter = jcas.getAnnotationIndex(InputDocument.type).iterator();
		  File outFile = null;
		  
		  while (docIter.hasNext()) {
		    	InputDocument doc = (InputDocument) docIter.next();
		    	Question question = doc.getQuestion();
		    	
		    	ArrayList<Answer> answersList = new ArrayList();
		    	int numCorrect = 0;
		    	//iterate through answers, calculating a score for each one 
		    	for (int i=0;i<doc.getAnswers().size();i++) {
		    		Answer answer = doc.getAnswers(i);
		    		int matches = 0;
		    		//iterate through ngrams, comparing them to the ones in the question
		    		for (int j=0;j<answer.getNgrams().size();j++) {
		    			String ngram = answer.getNgrams(j).getCoveredText();
		    			if (matches(ngram, question)) {
		    				matches++;
		    			}
		    		}
		    		answer.setScore(matches / question.getNgrams().size());
		    		answersList.add(answer);
		    		
		    		//keep track of the number of answers that are actually correct
		    		if (answer.getLabel()) {
		    			numCorrect++;
		    		}
		    	}
		    	
		    	//rank the answers by putting them in an array list and then sorting it
		    	//surely there's a more sensible way to do this?
		    	Collections.sort(answersList);
		    	
		    	//calculate precision
		    	int truePos=0;
		    	for (int i=0;i<numCorrect;i++) {
		    		if (answersList.get(i).getLabel()) {
		    			truePos++;
		    		}
		    	}
		    	double precision = truePos / numCorrect;
		    	
		    	
		    	//write file
		    	/*
		    	System.out.println("\n\n");
		    	mDocNum++;
		    	PrintWriter writer;
				try {
					writer = new PrintWriter(mOutputDir+"/a"+mDocNum+".txt", "UTF-8");
			    	writer.println(precision);
			    	System.out.println(precision);
			    	for (Answer answer : answersList) {
			    		writer.println(answer.getScore());
			    		System.out.println(answer.getScore());
			    	}
			    	writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
				
		    	try {

					String content = "This is the content to write into file";

					File file = new File("src/main/resources/outputData/test.txt");
					System.out.println(file.getAbsolutePath());

					// if file doesn't exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
		    	
		    	
		    }


	}
	
	public boolean matches(String ngram, Question question) {
		for (int i=0; i<question.getNgrams().size();i++) {
			if (question.getNgrams(i).getCoveredText().equals(ngram)) {
				return true;
			}
		}
		return false;
	}

}
