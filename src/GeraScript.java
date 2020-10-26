import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeraScript {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Map<String,String> campos = new HashMap<String,String>();
		campos.put("id","numero");
		campos.put("nome","string");
		campos.put("dia","data");
		
		List<String> listaDeTipos= campos.values().stream().collect(Collectors.toList());
		List<String> listaDeCampos = campos.keySet().stream().collect(Collectors.toList());
	
		String origem = "origem";
		String destino = "destino";
		
		String inicioInsert = "SELECT \'INSERT INTO " + destino + "(";
		String values = " VALUES (";
		String finalInsert = " FROM " + origem;
		
		FileWriter arq = new FileWriter("C:\\Users\\lopes\\teste\\teste.sql");
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for(int i = 0; i < listaDeCampos.size();i++) {
			switch(listaDeTipos.get(i)) {
				case "numero":
					values += "CONVERT(VARCHAR," + listaDeCampos.get(i) + ")";
				break;
				case "string":
					values += "\'\'\' + CONVERT(VARCHAR," +  listaDeCampos.get(i) + ") + \'\'\'";
				break;
				case "data":
					values += "\'\'\' + CONVERT(VARCHAR," +  listaDeCampos.get(i) + ", 110) + \'\'\'";
				break;
			}
			
			if(i == listaDeCampos.size() - 1) {
				inicioInsert += listaDeCampos.get(i) + ")";
				values += ")\'";
					
			}else {
				inicioInsert += listaDeCampos.get(i) + ",";
				values += ",";
			}
			
		}
		
		System.out.println(inicioInsert + values + finalInsert);
		gravarArq.printf(inicioInsert + values + finalInsert);
		arq.close();		
	}

}
