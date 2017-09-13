package creator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.ClassNullException;
import helper.StaticHelper;
import model.Variablen;

public class FileCreator {

	private File file;
	private String path;
	private String dirName = "mapper";
	private String filename;
	private Class<?> entity;
	private List<String> imports;
	private List<Variablen> variablen;
	private List<String> collectionClasses = getCollectionClassNames();

	public FileCreator(Class<?> entity) throws ClassNullException, IOException {
		if (entity == null)
			throw new ClassNullException();
		this.filename = entity.getSimpleName() + "Mapper";
		this.entity = entity;
		init();
	}

	private void init() {
		path = System.getProperty("user.dir") + "\\src";
		file = new File(path);
	}

	public void createMapper() throws IOException {
		createMapperPackage();
	}

	public void createDTO() {

	}

	public void createDAO() {

	}

	private void createMapperPackage() throws IOException {
		if (!mapperPackageAlreadyExist()) {
			System.out.println("Verzeichnis 'mapper' ist nicht vorhanden, weshalb es erstellt wird.");
			File dir = new File(path + "\\" + dirName);
			if (dir.mkdir())
				System.out.println("Das Verzeichnis 'mapper' wurde erfolgreich erstellt");
		}
		createMapperFile();
	}

	private void createMapperFile() throws IOException {
		File mapperFile = new File(path + "\\" + dirName + "\\" + filename + ".java");
		if (mapperFile.exists())
			mapperFile.delete();
		mapperFile.createNewFile();
		System.out.println("Die unbefüllte Datei " + filename + ".java wurde im package 'mapper' erfolgreich erstellt");
		fillMapperFile(mapperFile);
	}

	private void fillMapperFile(File file) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		pw.println("package mapper;");
		ermittleImportsUndVariablen();
		// schreibe imports
		schreibeImports(pw);
		// schreibe public class NAME{
		pw.printf("public class %s {%n%n", entity.getSimpleName());
		// schreibe Modifier Datentyp attrName
		schreibeVariablen(pw);
		// schreibe serialize Methode (Entity to DTO)
		
		// schreibe unserialize Methode (DTO to Entity)
		pw.println("}");

		pw.close();
		System.out.printf("Die %s Datei wurde erfolgreich befüllt%n", filename);
	}

	private void ermittleImportsUndVariablen() {
		// Beim auslesen der Variablen kriegt man auch die Imports, weshalb
		// beides in einem sein sollte
		List<Class<?>> noPrimitiveTypes = getNotPrimitiveClasses();
		if (this.entity.getDeclaredFields() != null && this.entity.getDeclaredFields().length > 0) {
			for (Field field : this.entity.getDeclaredFields()) {
				Variablen var = new Variablen();
				var.setModifier(field.getModifiers());
				var.setDatatype(field.getType().getSimpleName());
				var.setName(field.getName());
				var.setType(field.getGenericType());
				variablen.add(var);
				if (!field.getType().isPrimitive() || !noPrimitiveTypes.contains(field.getType()))
					imports.add("import " + field.getGenericType().toString() + ";");
			}
		}
	}

	private void schreibeImports(PrintWriter pw) {
		for (String string : imports) {
			pw.println(string);
		}
		pw.println();
	}

	private void schreibeVariablen(PrintWriter pw) {
		// Schreibe variablen
		for (Variablen var : variablen) {
			pw.printf("%s %s %s;%n", var.getModifier(),
					collectionClasses.contains(var.getDatatype())
							? var.getDatatype() + "<" + var.getType().toString() + ">" : var.getDatatype(),
					var.getName());
		}
		pw.println();
	}

	private void schreibeGetterUndSetter(PrintWriter pw) {
		// Schreibe Getter & Setter anhand der variablen
		boolean getter = StaticHelper.getInstance().isGetter();
		boolean setter = StaticHelper.getInstance().isSetter();
		int count = !getter && setter ? 2 : !getter && !setter ? 3 : 1;
		while (count <= 2) {
			if (count == 1) {
				schreibeGetter(pw);
			} else {
				schreibeSetter(pw);
			}
			if (getter && !setter)
				return;
			count++;
		}
	}

	private void schreibeGetter(PrintWriter pw) {
		for (Variablen var : variablen) {
			if (Modifier.isPrivate(var.getModifier()) || Modifier.isProtected(var.getModifier())) {
				String datatypeFull = collectionClasses.contains(var.getDatatype())
						? var.getDatatype() + "<" + var.getType().toString() + ">" : var.getDatatype();
				pw.printf("%s %s %s%s(%s %s){%n", var.getModifier(), datatypeFull, "get",
						var.getName().substring(0, 1).toUpperCase() + var.getName().substring(1), datatypeFull,
						var.getName());
				pw.printf("return this.%s;%n", var.getName());

				pw.println("}");
				pw.println();
			}
		}
	}

	private void schreibeSetter(PrintWriter pw) {
		for (Variablen var : variablen) {
			if (Modifier.isPrivate(var.getModifier()) || Modifier.isProtected(var.getModifier())) {
				String datatypeFull = collectionClasses.contains(var.getDatatype())
						? var.getDatatype() + "<" + var.getType().toString() + ">" : var.getDatatype();
				pw.printf("%s void %s%s(%s %s){%n", var.getModifier(), "set",
						var.getName().substring(0, 1).toUpperCase() + var.getName().substring(1), datatypeFull,
						var.getName());
				pw.printf("this.%s = %s;%n", var.getName(), var.getName());

				pw.println("}");
				pw.println();
			}
		}
	}
	
	private void schreibeMapToDTO(){
		
	}
	
	private void schreibeMapToEntity(){
		
	}

	private boolean mapperPackageAlreadyExist() {
		if (file != null && file.listFiles() != null && !Arrays.asList(file.listFiles()).isEmpty()) {
			for (File file : file.listFiles()) {
				if (file.getName().equalsIgnoreCase("mapper"))
					return true;
			}
		}
		return false;
	}

	private List<String> getCollectionClassNames() {
		List<String> collectionClasses = new ArrayList<>();
		collectionClasses.add("List");
		collectionClasses.add("Collection");
		collectionClasses.add("HashMap");
		collectionClasses.add("Set");
		collectionClasses.add("Vector");
		return collectionClasses;
	}

	private List<Class<?>> getNotPrimitiveClasses() {
		List<Class<?>> ret = new ArrayList<>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String[].class);
		ret.add(Integer[].class);
		ret.add(Byte[].class);
		ret.add(Character[].class);
		ret.add(Short[].class);
		ret.add(Long[].class);
		ret.add(Float[].class);
		ret.add(Double[].class);
		ret.add(Void[].class);
		return ret;
	}

}
