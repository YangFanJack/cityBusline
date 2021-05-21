package link.jack1024.service;

import link.jack1024.pojo.Notice;

import java.util.ArrayList;

public interface NoticeService {
    public boolean add(Notice notice);

    public ArrayList<Notice> findALl();

    public Notice findById(int id);

    public boolean delete(int id);

    public ArrayList<Notice> findAllBySearch(String serachString);
}
