package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public List<NoteBook> findBooksByUser(String userId);
	public int save(NoteBook book);
	public NoteBook findByNameAndUserId(Map<String, Object> params);
	public int addBook(NoteBook notebook);
}