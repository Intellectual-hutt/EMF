package mdse.emf.main;

import java.io.IOException;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;

import mdse.emf.util.UMLClassDiagramFactory;
import mdse.emf.util.ModelLoader;

/**
 * A class that creates UML class diagram.
 * 
 * @author Hassan Sartaj
 * @version 1.0
 */
public class UMLClassDiagramCreator {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		UMLClassDiagramFactory umlElem = new UMLClassDiagramFactory();
		//create package
		org.eclipse.uml2.uml.Package pkg = umlElem.createPackage("Demo Package");
		//create classes
		org.eclipse.uml2.uml.Class class1 = umlElem.createClass(pkg, "Student", false);
		org.eclipse.uml2.uml.Class class2 = umlElem.createClass(pkg, "Course", false);
		//create attributes in each class
		//class1
		org.eclipse.uml2.uml.PrimitiveType pt = umlElem.createPrimitiveType(pkg, "String");
		Property attribute1 = umlElem.createProperty(class1, "name", pt, 1, 1);
		attribute1.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		org.eclipse.uml2.uml.PrimitiveType pt1 = umlElem.createPrimitiveType(pkg, "Integer");
		Property attribute2 = umlElem.createProperty(class1, "rollNo", pt1, 1, 1);
		attribute2.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		//class2
		org.eclipse.uml2.uml.PrimitiveType pt2 = umlElem.createPrimitiveType(pkg, "String");
		Property attribute3 = umlElem.createProperty(class2, "title", pt2, 1, 1);
		attribute3.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		org.eclipse.uml2.uml.PrimitiveType pt3 = umlElem.createPrimitiveType(pkg, "Integer");
		Property attribute4 = umlElem.createProperty(class2, "code", pt3, 1, 1);
		attribute4.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		
		//create operations in class
		EList opArgs = new BasicEList();	//operation arguments
		opArgs.add("grade");
		EList opArgsType =  new BasicEList();	//argument types 
		opArgsType.add((Type) pt2);
		
		umlElem.createOperation(class1, "calculateGPA", (EList<String>) opArgs, opArgsType, pt3);
		
		//create association between class1 and class2
		Association association = umlElem.createAssociation(class1, false, AggregationKind.NONE_LITERAL, "course", 1, -1, class2, false, AggregationKind.NONE_LITERAL, "student", 0, 1);
		association.setName("register");
		//load model resources
		ModelLoader umlModel = new ModelLoader();
		umlModel.loadResources();
		//save model
		save(pkg, URI.createURI("OutputModel").appendSegment("ClassDiagram").appendFileExtension(UMLResource.FILE_EXTENSION));
	}
	/**
	 * A method which saves the supplied uml model on a uml file
	 * 
	 * @param package_
	 *            which contains the resultant uml model.
	 * @param uri
	 *            which contains the entire destination path where the file
	 *            would be saved.
	 */
	public static void save(org.eclipse.uml2.uml.Package package_, URI uri) {
		Resource resource = new ResourceSetImpl().createResource(uri);
		resource.getContents().add(package_);

		try {
			resource.save(null);
			System.out.println("Saved .....");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}