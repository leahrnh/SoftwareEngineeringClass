

/* First created by JCasGen Wed Sep 23 15:09:37 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Stores the information of the passage.
 * Updated by JCasGen Wed Sep 23 15:09:37 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi4-lnicolic/src/main/resources/typeSystem.xml
 * @generated */
public class Passage extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Passage.class);
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
  protected Passage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Passage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Passage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Passage(JCas jcas, int begin, int end) {
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
  //* Feature: questionId

  /** getter for questionId - gets This is the ID of the question it intends to answer to.
   * @generated
   * @return value of the feature 
   */
  public String getQuestionId() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_questionId == null)
      jcasType.jcas.throwFeatMissing("questionId", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_questionId);}
    
  /** setter for questionId - sets This is the ID of the question it intends to answer to. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestionId(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_questionId == null)
      jcasType.jcas.throwFeatMissing("questionId", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_questionId, v);}    
   
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets The passage text extracted from the source document.
   * @generated
   * @return value of the feature 
   */
  public String getSentence() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sentence);}
    
  /** setter for sentence - sets The passage text extracted from the source document. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentence(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sentence, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not.
   * @generated
   * @return value of the feature 
   */
  public boolean getLabel() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(boolean v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: sourceDocId

  /** getter for sourceDocId - gets This specifies the source document id to which the passage belongs to.
   * @generated
   * @return value of the feature 
   */
  public String getSourceDocId() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId);}
    
  /** setter for sourceDocId - sets This specifies the source document id to which the passage belongs to. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSourceDocId(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId, v);}    
  }

    