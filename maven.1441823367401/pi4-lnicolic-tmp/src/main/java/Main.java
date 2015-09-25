import java.io.IOException;
import java.util.List;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CasConsumer;
import org.apache.uima.collection.CollectionProcessingManager;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.EntityProcessStatus;
import org.apache.uima.collection.StatusCallbackListener;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.XMLInputSource;

/**
 * Main file programatically performs functions of the CPE. Modeled off SimpleRunCPM.java
 */
public class Main extends Thread {
  /**
   * The Collection Processing Manager instance that coordinates the processing.
   */
  private CollectionProcessingManager mCPM;

  /**
   * Start time of the processing - used to compute elapsed time.
   */
  private long mStartTime;

  /**
   * Constructor for the class.
   * 
   * @param args
   *          command line arguments into the program - see class description
   */
  public Main(String args[]) throws UIMAException, IOException {
    mStartTime = System.currentTimeMillis();

    // check command line args
    if (args.length < 3) {
      printUsageMessage();
      System.exit(1);
    }

    // create components from their descriptors

    // Collection Reader
    System.out.println("Initializing Collection Reader");
    ResourceSpecifier colReaderSpecifier = UIMAFramework.getXMLParser()
            .parseCollectionReaderDescription(new XMLInputSource("src/main/resources/collectionReader.xml"));
    CollectionReader collectionReader = UIMAFramework.produceCollectionReader(colReaderSpecifier);
    collectionReader.setConfigParameterValue("inputDir", args[1]);
    collectionReader.reconfigure();

    // AnalysisEngine
    System.out.println("Initializing AnalysisEngine");
    ResourceSpecifier aeSpecifier = UIMAFramework.getXMLParser().parseResourceSpecifier(
            new XMLInputSource("src/main/resources/TestElementAnnotatorAAE.xml"));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aeSpecifier);
    ae.setConfigParameterValue("N", args[0]);
    ae.reconfigure();

    // CAS Consumer
    System.out.println("Initializing CAS Consumer");
    ResourceSpecifier consumerSpecifier = UIMAFramework.getXMLParser().parseCasConsumerDescription(
            new XMLInputSource("src/main/resources/casConsumer.xml"));
    CasConsumer casConsumer = UIMAFramework.produceCasConsumer(consumerSpecifier);
    casConsumer.setConfigParameterValue("outputDir", args[2]);
    casConsumer.reconfigure();

    // create a new Collection Processing Manager
    mCPM = UIMAFramework.newCollectionProcessingManager();

    // Register AE and CAS Consumer with the CPM
    mCPM.setAnalysisEngine(ae);
    mCPM.addCasConsumer(casConsumer);

    // Create and register a Status Callback Listener
    mCPM.addStatusCallbackListener(new StatusCallbackListenerImpl());

    // Finish setup
    mCPM.setPauseOnException(false);

    // Start Processing (in batches of 10, just for testing purposes)
    mCPM.process(collectionReader, 10);
  }

  
  private static void printUsageMessage() {
    System.out.println(" Arguments to the program are as follows : \n"
            + "args[0] : NGram number \n "
            + "args[1] : input directory \n"
            + "args[2] : output directory");
  }

  /**
   * main class.
   * 
   * @param args
   *          Command line arguments - see class description
   */
  public static void main(String[] args) throws UIMAException, IOException {
    new Main(args);
  }

  /**
   * Callback Listener. Receives event notifications from CPM.
   * 
   * 
   */
  class StatusCallbackListenerImpl implements StatusCallbackListener {
    int entityCount = 0;

    long size = 0;

    /**
     * Called when the initialization is completed.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#initializationComplete()
     */
    public void initializationComplete() {
      System.out.println("CPM Initialization Complete");
    }

    /**
     * Called when the batchProcessing is completed.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#batchProcessComplete()
     * 
     */
    public void batchProcessComplete() {
      System.out.print("Completed " + entityCount + " documents");
      if (size > 0) {
        System.out.print("; " + size + " characters");
      }
      System.out.println();
      long elapsedTime = System.currentTimeMillis() - mStartTime;
      System.out.println("Time Elapsed : " + elapsedTime + " ms ");
    }

    /**
     * Called when the collection processing is completed.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#collectionProcessComplete()
     */
    public void collectionProcessComplete() {
      System.out.print("Completed " + entityCount + " documents");
      if (size > 0) {
        System.out.print("; " + size + " characters");
      }
      System.out.println();
      long elapsedTime = System.currentTimeMillis() - mStartTime;
      System.out.println("Time Elapsed : " + elapsedTime + " ms ");
      System.out.println("\n\n ------------------ PERFORMANCE REPORT ------------------\n");
      System.out.println(mCPM.getPerformanceReport().toString());
    }

    /**
     * Called when the CPM is paused.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#paused()
     */
    public void paused() {
      System.out.println("Paused");
    }

    /**
     * Called when the CPM is resumed after a pause.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#resumed()
     */
    public void resumed() {
      System.out.println("Resumed");
    }

    /**
     * Called when the CPM is stopped abruptly due to errors.
     * 
     * @see org.apache.uima.collection.processing.StatusCallbackListener#aborted()
     */
    public void aborted() {
      System.out.println("Aborted");
    }

    /**
     * Called when the processing of a Document is completed. <br>
     * The process status can be looked at and corresponding actions taken.
     * 
     * @param aCas
     *          CAS corresponding to the completed processing
     * @param aStatus
     *          EntityProcessStatus that holds the status of all the events for aEntity
     */
    public void entityProcessComplete(CAS aCas, EntityProcessStatus aStatus) {
      if (aStatus.isException()) {
        List exceptions = aStatus.getExceptions();
        for (int i = 0; i < exceptions.size(); i++) {
          ((Throwable) exceptions.get(i)).printStackTrace();
        }
        return;
      }
      entityCount++;
      String docText = aCas.getDocumentText();
      if (docText != null) {
        size += docText.length();
      }
    }
  }

}
