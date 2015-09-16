

/* First created by JCasGen Mon Sep 14 08:56:03 EDT 2015 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Annotates answer Sentences in the input.
 * Updated by JCasGen Mon Sep 14 17:13:57 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi2-lnicolic/src/main/resources/pi2-lnicolic-typesystem.xml
 * @generated */
public class Answer extends Sentence {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Answer.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Answer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Answer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Answer(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Answer(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: correct

  /** getter for correct - gets whether the answer is a correct answer to the question
   * @generated
   * @return value of the feature 
   */
  public boolean getCorrect() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_correct == null)
      jcasType.jcas.throwFeatMissing("correct", "Answer");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_correct);}
    
  /** setter for correct - sets whether the answer is a correct answer to the question 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCorrect(boolean v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_correct == null)
      jcasType.jcas.throwFeatMissing("correct", "Answer");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Answer_Type)jcasType).casFeatCode_correct, v);}    
   
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets a number between 0 and 1 representing how well the Answer matches its associated Question.
   * @generated
   * @return value of the feature 
   */
  public float getScore() {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "Answer");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Answer_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets a number between 0 and 1 representing how well the Answer matches its associated Question. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore(float v) {
    if (Answer_Type.featOkTst && ((Answer_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "Answer");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Answer_Type)jcasType).casFeatCode_score, v);}    
  }

    