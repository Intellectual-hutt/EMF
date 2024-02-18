package mdse.emf.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * A class that creates UML model elements (e.g. package, class, attribute, etc)
 * 
 * @author Hassan Sartaj
 * @version 1.0
 */

public class UMLClassDiagramFactory {

	public UMLClassDiagramFactory() {
	}

	/**
	 * A method that creates and returns UML model and set its name as specified in parameter.
	 * 
	 * @param name: String
	 * @return uml model: Model
	 */
	public Model createModel(String name) {
		Model model = UMLFactory.eINSTANCE.createModel();
		model.setName(name);
		return model;
	}

	/**
	 * A method that creates and returns UML package and set its name as specified in parameter.
	 * 
	 * @param name: String
	 * @return _package: org.eclipse.uml2.uml.Package
	 */
	public org.eclipse.uml2.uml.Package createPackage(String name) {
		org.eclipse.uml2.uml.Package _package= UMLFactory.eINSTANCE.createPackage();
		_package.setName(name);
		return _package;
	}

	/**
	 * A method that creates and returns UML class in specified package in parameter. 
	 * 
	 * @param _package: org.eclipse.uml2.uml.Package
	 * @param name: String
	 * @param isAbstract: boolean
	 * @return _class: org.eclipse.uml2.uml.Class
	 */
	public org.eclipse.uml2.uml.Class createClass(org.eclipse.uml2.uml.Package _package, String name, boolean isAbstract) {
		org.eclipse.uml2.uml.Class _class = _package.createOwnedClass(name, isAbstract);
		return _class;
	}

	/**
	 * A method that creates and returns UML property in specified class. 
	 * 
	 * @param _class: org.eclipse.uml2.uml.Class
	 * @param name: String
	 * @param type: Type
	 * @param lowerBound: int
	 * @param upperBound: int
	 * @return attribute: Property
	 */
	public Property createProperty(org.eclipse.uml2.uml.Class _class, String name, Type type, int lowerBound, int upperBound) {
		Property attribute = _class.createOwnedAttribute(name, type, lowerBound, upperBound);
		return attribute;
	}
	
	
	/**
	 * A method that creates and returns UML operation in specified class.
	 * 
	 * @param _class
	 * @param name
	 * @param args
	 * @param argsType
	 * @param type
	 * @return uml operation
	 */
	public Operation createOperation(org.eclipse.uml2.uml.Class _class, String name,EList<String>args,EList<Type> argsType , Type type) {
		Operation operation = _class.createOwnedOperation(name, args, argsType, type);
		return operation;
	}

	/**
	 * A method that creates and returns EnumerationLiteral element in specified enumeration in parameter. 
	 * 
	 * @param enumeration: Enumeration
	 * @param name: String
	 * @return enumerationLiteral: EnumerationLiteral
	 */
	public EnumerationLiteral createEnumerationLiteral(Enumeration enumeration, String name) {
		EnumerationLiteral enumerationLiteral = enumeration.createOwnedLiteral(name);
		return enumerationLiteral;
	}

	/**
	 * A method that creates and returns PrimitiveType element in specified package with given name in parameter. 
	 * 
	 * @param package_: org.eclipse.uml2.uml.Package
	 * @param name: String
	 * @return primitiveType: PrimitiveType
	 */
	public PrimitiveType createPrimitiveType(org.eclipse.uml2.uml.Package package_, String name) {
		PrimitiveType primitiveType = package_.createOwnedPrimitiveType(name);
		return primitiveType;
	}
	
	/**
	 * A method that creates and returns Enumeration element in specified package with given name in parameter.
	 * 
	 * @param package_: org.eclipse.uml2.uml.Package
	 * @param name: String
	 * @return enumeration: Enumeration
	 */
	public Enumeration createEnumeration(org.eclipse.uml2.uml.Package package_, String name) {
		Enumeration enumeration = package_.createOwnedEnumeration(name);
		return enumeration;
	}
	
	/**
	 * A method that creates and returns UML Association element with the specified properties in parameter.
	 * 
	 * @param type1: Type
	 * @param end1IsNavigable: boolean
	 * @param end1Aggregation: AggregationKind
	 * @param end1Name: String
	 * @param end1LowerBound: int
	 * @param end1UpperBound: int
	 * @param type2: Type
	 * @param end2IsNavigable: boolean
	 * @param end2Aggregation: AggregationKind
	 * @param end2Name: String
	 * @param end2LowerBound: int
	 * @param end2UpperBound: int
	 * @return association: Association
	 */
	public Association createAssociation(Type type1, boolean end1IsNavigable, AggregationKind end1Aggregation, String end1Name, int end1LowerBound, int end1UpperBound,
            Type type2, boolean end2IsNavigable, AggregationKind end2Aggregation, String end2Name, int end2LowerBound, int end2UpperBound) {

        Association association = type1.createAssociation(end1IsNavigable, end1Aggregation, end1Name, end1LowerBound, end1UpperBound,
            type2, end2IsNavigable, end2Aggregation, end2Name, end2LowerBound, end2UpperBound);
        return association;
    }
}
