/**
 * 
 */
package mdse.emf.main;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import mdse.emf.util.ModelLoader;

/**
 * A class that reads Ecore meta model and prints its elements.
 * 
 * @author Hassan Sartaj
 * @version 1.0
 */
public class EcoreClassDiagramReader {

	public static void main(String[] args) {
		ModelLoader umlModel = new ModelLoader();
		String umlFilePath = "Examples/EcoreExample.ecore";
		String uri = null;
		try {
			uri = umlModel.getFileURI(umlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object objModel = umlModel.loadModel(uri);
		if (objModel instanceof EPackage) {
			EPackage ePackage = (EPackage) objModel;
			EList<EClassifier> classifiers = ePackage.getEClassifiers();
			for(EClassifier eClassifier : classifiers) {
				System.out.println("EClass:: "+eClassifier.getName());
				Iterator<EObject> it = eClassifier.eAllContents();
				while (it.hasNext()) {
					EObject eobj = it.next();
					if(eobj instanceof EAttribute) {
						EAttribute eAttribute = (EAttribute) eobj;
						System.out.println("EAttribute:: "+eAttribute.getName());
					}
				}
			}
		}
	}

}
