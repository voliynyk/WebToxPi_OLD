package edu.tamu.webtoxpi.Server.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import edu.tamu.webtoxpi.Server.ImportData.DataTransformation;
import edu.tamu.webtoxpi.Server.ImportData.FileData;
import edu.tamu.webtoxpi.Server.ImportData.FileProcessor;

@Path("/file")
public class UploadFileService
{

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail)
	{
		if (StringUtils.isNotBlank(fileDetail.getFileName()))
		{
			String userLocation = System.getProperty("user.home") + "\\" + "WebToxPi";
			File file = new File(userLocation);
			
			if (!file.exists())
			{
				if (file.mkdir())
				{
					System.out.println("Directory is created!");
				}
				else
				{
					System.out.println("Failed to create directory!");
				}
			}
			
			String fileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "csv");
			String uploadedFileLocation = userLocation + "\\" + fileName;
			writeToFile(uploadedInputStream, uploadedFileLocation, fileDetail.getFileName());
		}
		
		return Response.temporaryRedirect(URI.create("../../WebToxPi")).build();

	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation, String originalFileName)
	{

		// TODO DELETE
		Map<String, FileData> fileDataList = new HashMap<String, FileData>();

		try
		{
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1)
			{
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			// TODO CHANGE !!!
			FileProcessor fp = new FileProcessor();
			try
			{
				fp.readFile(uploadedFileLocation, fileDataList, originalFileName);
			}
			catch (Exception ex)
			{
			}

			FileData fd = fp.getFileData();
			DataTransformation dt = new DataTransformation(fd);
			dt.Transform();
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}

	}

}