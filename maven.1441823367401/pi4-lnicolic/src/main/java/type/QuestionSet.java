

/* First created by JCasGen Wed Sep 23 23:36:16 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;


/** A grouping of the Question and Passages associated with a single question ID.
 * Updated by JCasGen Wed Sep 30 19:19:10 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi4-lnicolic/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class QuestionSet extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(QuestionSet.class);
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
  protected QuestionSet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public QuestionSet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public QuestionSet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public QuestionSet(JCas jcas, int begin, int end) {
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
  //* Feature: question

  /** getter for question - gets 
   * @generated
   * @return value of the feature 
   */
  public Question getQuestion() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.QuestionSet");
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_question)));}
    
  /** setter for question - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(Question v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.QuestionSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_question, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: passages

  /** getter for passages - gets All the passages associated with a question ID
   * @generated
   * @return value of the feature 
   */
  public FSArray getPassages() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_passages == null)
      jcasType.jcas.throwFeatMissing("passages", "type.QuestionSet");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages)));}
    
  /** setter for passages - sets All the passages associated with a question ID 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPassages(FSArray v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_passages == null)
      jcasType.jcas.throwFeatMissing("passages", "type.QuestionSet");
    jcasType.ll_cas.ll_setRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for passages - gets an indexed value - All the passages associated with a question ID
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public Passage getPassages(int i) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_passages == null)
      jcasType.jcas.throwFeatMissing("passages", "type.QuestionSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages), i);
    return (Passage)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages), i)));}

  /** indexed setter for passages - sets an indexed value - All the passages associated with a question ID
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setPassages(int i, Passage v) { 
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_passages == null)
      jcasType.jcas.throwFeatMissing("passages", "type.QuestionSet");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_passages), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: pAt1

  /** getter for pAt1 - gets Precision at 1
   * @generated
   * @return value of the feature 
   */
  public double getPAt1() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_pAt1 == null)
      jcasType.jcas.throwFeatMissing("pAt1", "type.QuestionSet");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_pAt1);}
    
  /** setter for pAt1 - sets Precision at 1 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPAt1(double v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_pAt1 == null)
      jcasType.jcas.throwFeatMissing("pAt1", "type.QuestionSet");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_pAt1, v);}    
   
    
  //*--------------*
  //* Feature: pAt5

  /** getter for pAt5 - gets Precision at 5
   * @generated
   * @return value of the feature 
   */
  public double getPAt5() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_pAt5 == null)
      jcasType.jcas.throwFeatMissing("pAt5", "type.QuestionSet");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_pAt5);}
    
  /** setter for pAt5 - sets Precision at 5 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPAt5(double v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_pAt5 == null)
      jcasType.jcas.throwFeatMissing("pAt5", "type.QuestionSet");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_pAt5, v);}    
   
    
  //*--------------*
  //* Feature: rr

  /** getter for rr - gets Reciprocal Rank
   * @generated
   * @return value of the feature 
   */
  public double getRr() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_rr == null)
      jcasType.jcas.throwFeatMissing("rr", "type.QuestionSet");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_rr);}
    
  /** setter for rr - sets Reciprocal Rank 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRr(double v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_rr == null)
      jcasType.jcas.throwFeatMissing("rr", "type.QuestionSet");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_rr, v);}    
   
    
  //*--------------*
  //* Feature: ap

  /** getter for ap - gets Average Precision
   * @generated
   * @return value of the feature 
   */
  public double getAp() {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_ap == null)
      jcasType.jcas.throwFeatMissing("ap", "type.QuestionSet");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_ap);}
    
  /** setter for ap - sets Average Precision 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAp(double v) {
    if (QuestionSet_Type.featOkTst && ((QuestionSet_Type)jcasType).casFeat_ap == null)
      jcasType.jcas.throwFeatMissing("ap", "type.QuestionSet");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((QuestionSet_Type)jcasType).casFeatCode_ap, v);}    
  }

    