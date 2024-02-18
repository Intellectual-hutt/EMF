package mdse.emf.main;

import mdse.emf.util.ModelLoader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;

public class Transformation {
	
	
	public static void main(String[] args) {
		
	
		ModelLoader umlModel = new ModelLoader();
		//String umlFilePath = "Examples/TestExample01.xmi";
		String umlFilePath = "Examples/UML project.uml";
		//String umlFilePath = "Examples/Haroon.uml";
		String uri = null;
		try {
			uri = umlModel.getFileURI(umlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object objModel = umlModel.loadModel(uri);
		Model sourceModel;
		EList<PackageableElement> sourcePackagedElements = null;
		if (objModel instanceof Model) {
			sourceModel = (Model) objModel;
			sourcePackagedElements = sourceModel.getPackagedElements();
		} else if (objModel instanceof Package) {
			Package sourcePackage = (Package) objModel;
			sourcePackagedElements = sourcePackage.getPackagedElements();
		}

		for (PackageableElement element : sourcePackagedElements){
			//for nested package
			if(element.eClass() == UMLPackage.Literals.PACKAGE){
				org.eclipse.uml2.uml.Package nestedPackage = (org.eclipse.uml2.uml.Package) element;
				EList<PackageableElement> nestedPackagedElements = nestedPackage.getPackagedElements();
				for (PackageableElement nestedElement : nestedPackagedElements){
					generate_code(nestedElement);
				}
			}
			else
				//printModelDetails(element);
				generate_code(element);
		}
	}
	
	private static void generate_code(PackageableElement element){
		String code ="package ";
		String space =" ";
		String header="import java.io.File;\nimport java.io.FileInputStream;\nimport java.util.Iterator;";
		
		if (element.eClass() == UMLPackage.Literals.STATE_MACHINE)
		{
			StateMachine statemachine=(StateMachine)element;
			//get class name
			String Name = statemachine.getName();
			code=code+Name+" "+"\n";
			code=code+header+"\n";
			code=code+"public Class";
			//System.out.print(code);
			//System.out.println("State machine Name: "+Name);
			EList<org.eclipse.uml2.uml.Region> region=statemachine.getRegions();
			for(org.eclipse.uml2.uml.Region r: region){
				//System.out.println(r.getName());
				EList<Vertex> state=r.getSubvertices();
				code=code+space+state.get(0).getLabel()+"{"+"\n";
				
				EList<Transition> trans=r.getTransitions();
				code+="Public void "+trans.get(0).getLabel()+"{"+"\n}";
				code+="Public Class "+state.get(1).getLabel()+"{\n";
				code+= "public void "+trans.get(1).getLabel()+"{\n }";
				
				for (Vertex v:state){
					//System.out.println("States: "+ v.getLabel());
					//code=code+v.getName();
					
				}
				for(Transition t:trans){
					//System.out.println("Events: "+t.getLabel());
					
				}
				System.out.print(code);
				
		}
	}
	
	}
	}
