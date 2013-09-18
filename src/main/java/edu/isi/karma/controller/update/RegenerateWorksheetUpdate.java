/*******************************************************************************
 * Copyright 2012 University of Southern California
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This code was developed by the Information Integration Group as part 
 * of the Karma project at the Information Sciences Institute of the 
 * University of Southern California.  For more information, publications, 
 * and related projects, please see: http://www.isi.edu/integration
 ******************************************************************************/

package edu.isi.karma.controller.update;

import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

import edu.isi.karma.rep.Worksheet;
import edu.isi.karma.view.VWorksheet;
import edu.isi.karma.view.VWorkspace;

public class RegenerateWorksheetUpdate extends AbstractUpdate {

	private String worksheetId;
	public RegenerateWorksheetUpdate(String worksheetId)
	{
		this.worksheetId = worksheetId;
	}
	@Override
	public void generateJson(String prefix, PrintWriter pw,
			VWorkspace vWorkspace) {
		JSONObject obj = new JSONObject();
		try {
			obj.put(GenericJsonKeys.updateType.name(), "regenerateworksheetupdate");
			pw.println(obj.toString());
		} catch (JSONException e) {
			System.err.println("ohno!");
		}

	}
	@Override
	public void applyUpdate(VWorkspace vWorkspace)
	{
		VWorksheet vWorksheet =  vWorkspace.getViewFactory().getVWorksheetByWorksheetId(worksheetId);
		if(vWorksheet == null)
		{
			System.out.println("this is a place to park");
		}
		Worksheet worksheet = vWorksheet.getWorksheet();
		vWorkspace.getViewFactory().updateWorksheet(vWorksheet.getId(), worksheet,
				worksheet.getHeaders().getAllPaths(), vWorkspace);
	}


}
