

/* First created by JCasGen Wed Sep 16 15:17:38 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Stores the information about a question.
 * Updated by JCasGen Wed Sep 23 15:09:37 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi4-lnicolic/src/main/resources/typeSystem.xml
 * @generated */
public class Question extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Question.class);
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
  protected Question() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Question(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Question(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Question(JCas jcas, int begin, int end) {
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
  //* Feature: id

  /** getter for id - gets The identifier for the question.
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets The identifier for the question. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets The text of the question.
   * @generated
   * @return value of the feature 
   */
  public String getSentence() {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Question");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Question_Type)jcasType).casFeatCode_sentence);}
    
  /** setter for sentence - sets The text of the question. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentence(String v) {
    if (Question_Type.featOkTst && ((Question_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Question");
    jcasType.ll_cas.ll_setStringValue(addr, ((Question_Type)jcasType).casFeatCode_sentence, v);}    
  }

    