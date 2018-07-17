package com.pole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

public class ProjectSet implements Serializable{
	private static final long serialVersionUID = 1L;
	private int num;
	private SubProject subProjectArray[];
	public ProjectSet(int projectNum) {
		num = projectNum;
		subProjectArray = new SubProject[projectNum];
		for(int i = 0; i < projectNum; i++) {
			subProjectArray[i] = new SubProject("0", 0, 0, 0, 0, 0, 0);
		}
	}
	
	public SubProject getSubProject(int index) {
		return subProjectArray[index];
	}
	public void setSubProject(int index, SubProject subP) throws OptionalDataException, IOException, ClassNotFoundException {
		subProjectArray[index] = (SubProject)subP.myCopy();
	}
	public int getSize() {
		return num;
	}

	//�����¡
    public Object myCopy() throws IOException,OptionalDataException,ClassNotFoundException
    {
    //������д������
    ByteArrayOutputStream bo=new ByteArrayOutputStream();
    ObjectOutputStream oo=new ObjectOutputStream(bo);
    oo.writeObject(this);
    //�����������
    ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
    ObjectInputStream oi=new ObjectInputStream(bi);
    return(oi.readObject());
    }

	public SubProject[] getSubProjectArray() {
		return subProjectArray;
	}

	
}
