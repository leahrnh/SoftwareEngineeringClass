package annotators;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.Passage;
import type.Question;
import type.QuestionSet;

/**
 * Annotates question lines from the input document
 *
 */
public class QuestionSetAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		
		// get annotation indexes
	    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
	    FSIndex passageIndex = aJCas.getAnnotationIndex(Passage.type);
		
	    
	    //iterate over Questions and store them in a map by ID
	    HashMap<String, Question> questionMap = new HashMap();
	    Iterator questionIter = questionIndex.iterator();
	    while (questionIter.hasNext()) {
	    	Question question = (Question) questionIter.next();
	    	questionMap.put(question.getId(), question);
	    }
	    
	    //iterate over Passages and store them in a map by ID
	    HashMap<String, ArrayList<Passage>> passageMap = new HashMap();
	    Iterator passageIter = passageIndex.iterator();
	    while (passageIter.hasNext()) {
	    	Passage passage = (Passage) passageIter.next();
	    	String qid =  passage.getQuestionId();
	    	if (passageMap.containsKey(qid)) {
	    		passageMap.get(qid).add(passage);
	    	} else {
	    		ArrayList<Passage> passageList = new ArrayList();
	    		passageMap.put(qid, passageList);
	    	}
	    }
	    
	    //iterate over IDs and convert them into QuestionSets
	    for (String id : questionMap.keySet()) {
	    	QuestionSet qs = new QuestionSet(aJCas);
	    	qs.setQuestion(questionMap.get(id));
	    	if (passageMap.containsKey(id)) {
	    		
	    		//get a list of the passages in ArrayList form
	    		ArrayList<Passage> passageList = passageMap.get(id);
	    		
	    		//convert the ArrayList to an Array
	    		Passage[] passageArray = new Passage[passageList.size()];
	    		passageArray = passageList.toArray(passageArray);
	    		
	    		//convert the Array to an FSArray
	    		FSArray passageFSArray = new FSArray(aJCas, passageArray.length); 
	    		passageFSArray.copyFromArray(passageArray, 0, 0, passageArray.length);
	    		
	    		//add it to QuestionSet
	    		qs.setPassages(passageFSArray);
	    		qs.addToIndexes();
	    	} else {
	    		System.err.println("Skipping question " + id + "because there are no associated passages.");
	    	}
	    }
	}
}
