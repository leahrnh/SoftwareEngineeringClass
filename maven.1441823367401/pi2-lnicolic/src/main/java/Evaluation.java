

/* First created by JCasGen Mon Sep 14 08:56:03 EDT 2015 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Ranks Answers by score and measures performance.
 * Updated by JCasGen Mon Sep 14 09:21:45 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi2-lnicolic/src/main/resources/pi2-lnicolic-typesystem.xml
 * @generated */
public class Evaluation extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Evaluation.class);
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
  protected Evaluation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Evaluation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Evaluation(JCas jcas) {
    super(jcas);
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
  //* Feature: n

  /** getter for n - gets The number of correct answers in the input.
   * @generated
   * @return value of the feature 
   */
  public int getN() {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "Evaluation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Evaluation_Type)jcasType).casFeatCode_n);}
    
  /** setter for n - sets The number of correct answers in the input. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setN(int v) {
    if (Evaluation_Type.featOkTst && ((Evaluation_Type)jcasType).casFeat_n == null)
      jcasType.jcas.throwFeatMissing("n", "Evaluation");
    jcasType.ll_cas.ll_setIntValue(addr, ((Evaluation_Type)jcasType).casFeatCode_n, v);}    
  }

    