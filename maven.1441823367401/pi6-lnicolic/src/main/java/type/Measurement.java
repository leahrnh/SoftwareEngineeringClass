

/* First created by JCasGen Tue Oct 06 22:21:35 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Holds various measurements used to calculate metrics
 * Updated by JCasGen Tue Oct 06 22:21:35 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi6-lnicolic/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Measurement extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Measurement.class);
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
  protected Measurement() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Measurement(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Measurement(JCas jcas) {
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
  //* Feature: tp

  /** getter for tp - gets True Positive
   * @generated
   * @return value of the feature 
   */
  public int getTp() {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_tp == null)
      jcasType.jcas.throwFeatMissing("tp", "type.Measurement");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_tp);}
    
  /** setter for tp - sets True Positive 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTp(int v) {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_tp == null)
      jcasType.jcas.throwFeatMissing("tp", "type.Measurement");
    jcasType.ll_cas.ll_setIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_tp, v);}    
   
    
  //*--------------*
  //* Feature: fn

  /** getter for fn - gets False Negative
   * @generated
   * @return value of the feature 
   */
  public int getFn() {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_fn == null)
      jcasType.jcas.throwFeatMissing("fn", "type.Measurement");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_fn);}
    
  /** setter for fn - sets False Negative 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFn(int v) {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_fn == null)
      jcasType.jcas.throwFeatMissing("fn", "type.Measurement");
    jcasType.ll_cas.ll_setIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_fn, v);}    
   
    
  //*--------------*
  //* Feature: fp

  /** getter for fp - gets False Positive
   * @generated
   * @return value of the feature 
   */
  public int getFp() {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_fp == null)
      jcasType.jcas.throwFeatMissing("fp", "type.Measurement");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_fp);}
    
  /** setter for fp - sets False Positive 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFp(int v) {
    if (Measurement_Type.featOkTst && ((Measurement_Type)jcasType).casFeat_fp == null)
      jcasType.jcas.throwFeatMissing("fp", "type.Measurement");
    jcasType.ll_cas.ll_setIntValue(addr, ((Measurement_Type)jcasType).casFeatCode_fp, v);}    
  }

    