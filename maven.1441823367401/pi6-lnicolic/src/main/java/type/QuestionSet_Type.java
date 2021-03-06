
/* First created by JCasGen Wed Sep 23 23:36:16 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A grouping of the Question and Passages associated with a single question ID.
 * Updated by JCasGen Tue Oct 06 22:21:35 EDT 2015
 * @generated */
public class QuestionSet_Type extends ComponentAnnotation_Type {
	
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (QuestionSet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = QuestionSet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new QuestionSet(addr, QuestionSet_Type.this);
  			   QuestionSet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new QuestionSet(addr, QuestionSet_Type.this);
  	  }
    };
    
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = QuestionSet.typeIndexID;
  
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.QuestionSet");
 
  /** @generated */
  final Feature casFeat_question;
  /** @generated */
  final int     casFeatCode_question;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuestion(int addr) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "type.QuestionSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_question);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestion(int addr, int v) {
        if (featOkTst && casFeat_question == null)
      jcas.throwFeatMissing("question", "type.QuestionSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_question, v);}
    
  
 
  /** @generated */
  final Feature casFeat_passages;
  /** @generated */
  final int     casFeatCode_passages;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPassages(int addr) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.QuestionSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_passages);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassages(int addr, int v) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.QuestionSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_passages, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getPassages(int addr, int i) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.QuestionSet");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setPassages(int addr, int i, int v) {
        if (featOkTst && casFeat_passages == null)
      jcas.throwFeatMissing("passages", "type.QuestionSet");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_passages), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_measurement;
  /** @generated */
  final int     casFeatCode_measurement;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMeasurement(int addr) {
        if (featOkTst && casFeat_measurement == null)
      jcas.throwFeatMissing("measurement", "type.QuestionSet");
    return ll_cas.ll_getRefValue(addr, casFeatCode_measurement);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMeasurement(int addr, int v) {
        if (featOkTst && casFeat_measurement == null)
      jcas.throwFeatMissing("measurement", "type.QuestionSet");
    ll_cas.ll_setRefValue(addr, casFeatCode_measurement, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public QuestionSet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_question = jcas.getRequiredFeatureDE(casType, "question", "type.Question", featOkTst);
    casFeatCode_question  = (null == casFeat_question) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_question).getCode();

 
    casFeat_passages = jcas.getRequiredFeatureDE(casType, "passages", "uima.cas.FSArray", featOkTst);
    casFeatCode_passages  = (null == casFeat_passages) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_passages).getCode();

 
    casFeat_measurement = jcas.getRequiredFeatureDE(casType, "measurement", "type.Measurement", featOkTst);
    casFeatCode_measurement  = (null == casFeat_measurement) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_measurement).getCode();

  }
}



    