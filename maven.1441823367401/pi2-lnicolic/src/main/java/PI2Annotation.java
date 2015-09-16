

/* First created by JCasGen Mon Sep 14 17:13:53 EDT 2015 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** An abstract Annotation encompassing all the Annotations in the PI2 project.
 * Updated by JCasGen Mon Sep 14 17:13:57 EDT 2015
 * XML source: /Users/leah/Tulip/CMU/Fall2015/Software Engineering/SoftwareEngineeringClass/maven.1441823367401/pi2-lnicolic/src/main/resources/pi2-lnicolic-typesystem.xml
 * @generated */
public class PI2Annotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PI2Annotation.class);
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
  protected PI2Annotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PI2Annotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PI2Annotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PI2Annotation(JCas jcas, int begin, int end) {
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
  //* Feature: confidence

  /** getter for confidence - gets the confidence score that the annotation is accurate
   * @generated
   * @return value of the feature 
   */
  public float getConfidence() {
    if (PI2Annotation_Type.featOkTst && ((PI2Annotation_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "PI2Annotation");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((PI2Annotation_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets the confidence score that the annotation is accurate 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfidence(float v) {
    if (PI2Annotation_Type.featOkTst && ((PI2Annotation_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "PI2Annotation");
    jcasType.ll_cas.ll_setFloatValue(addr, ((PI2Annotation_Type)jcasType).casFeatCode_confidence, v);}    
   
    
  //*--------------*
  //* Feature: annotator

  /** getter for annotator - gets the name of the annotator component that created this annotation.
   * @generated
   * @return value of the feature 
   */
  public String getAnnotator() {
    if (PI2Annotation_Type.featOkTst && ((PI2Annotation_Type)jcasType).casFeat_annotator == null)
      jcasType.jcas.throwFeatMissing("annotator", "PI2Annotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PI2Annotation_Type)jcasType).casFeatCode_annotator);}
    
  /** setter for annotator - sets the name of the annotator component that created this annotation. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnnotator(String v) {
    if (PI2Annotation_Type.featOkTst && ((PI2Annotation_Type)jcasType).casFeat_annotator == null)
      jcasType.jcas.throwFeatMissing("annotator", "PI2Annotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((PI2Annotation_Type)jcasType).casFeatCode_annotator, v);}    
  }

    