/* Apache UIMA v3 - First created by JCasGen Wed Apr 17 13:42:16 BST 2019 */

package uk.gov.dstl.baleen.types.structure;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;

import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.impl.TypeSystemImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/**
 * A part of a Table. Updated by JCasGen Wed Apr 17 13:42:16 BST 2019 XML source:
 * types/structure_type_system.xml
 *
 * @generated
 */
public class TablePart extends Structure {

  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public static final String _TypeName = "uk.gov.dstl.baleen.types.structure.TablePart";

  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public static final int typeIndexID = JCasRegistry.register(TablePart.class);
  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public static final int type = typeIndexID;
  /**
   * @generated
   * @return index of the type
   */
  @Override
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /* *******************
   *   Feature Offsets *
   * *******************/

  public static final String _FeatName_table = "table";

  /* Feature Adjusted Offsets */
  private static final CallSite _FC_table = TypeSystemImpl.createCallSite(TablePart.class, "table");
  private static final MethodHandle _FH_table = _FC_table.dynamicInvoker();

  /**
   * Never called. Disable default constructor
   *
   * @generated
   */
  protected TablePart() {
    /* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   *
   * @generated
   * @param casImpl the CAS this Feature Structure belongs to
   * @param type the type of this Feature Structure
   */
  public TablePart(TypeImpl type, CASImpl casImpl) {
    super(type, casImpl);
    readObject();
  }

  /**
   * @generated
   * @param jcas JCas to which this Feature Structure belongs
   */
  public TablePart(JCas jcas) {
    super(jcas);
    readObject();
  }

  /**
   * @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA
   */
  public TablePart(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable
   */
  private void readObject() {
    /*default - does nothing empty block */
  }

  // *--------------*
  // * Feature: table

  /**
   * getter for table - gets The table this is part of
   *
   * @generated
   * @return value of the feature
   */
  public Table getTable() {
    return (Table) (_getFeatureValueNc(wrapGetIntCatchException(_FH_table)));
  }

  /**
   * setter for table - sets The table this is part of
   *
   * @generated
   * @param v value to set into the feature
   */
  public void setTable(Table v) {
    _setFeatureValueNcWj(wrapGetIntCatchException(_FH_table), v);
  }
}
