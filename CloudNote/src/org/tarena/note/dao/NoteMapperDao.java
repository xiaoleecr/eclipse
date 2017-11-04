package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;

public interface NoteMapperDao {
	public List<Map<String,Object>> findNotesByBookId(String bookId);
	public int save(Note note);
}