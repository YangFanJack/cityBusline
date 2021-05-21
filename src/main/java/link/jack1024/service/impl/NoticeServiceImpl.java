package link.jack1024.service.impl;

import link.jack1024.dao.NoticeDao;
import link.jack1024.pojo.Notice;
import link.jack1024.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.traversal.NodeIterator;

import java.util.ArrayList;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public boolean add(Notice notice) {
        noticeDao.insert(notice);
        return true;
    }

    @Override
    public ArrayList<Notice> findALl() {
        ArrayList<Notice> notices = noticeDao.selectAll();
        return notices;
    }

    @Override
    public Notice findById(int id) {
        Notice notice = noticeDao.selectById(id);
        return notice;
    }

    @Override
    public boolean delete(int id) {
        noticeDao.delete(id);
        return true;
    }

    @Override
    public ArrayList<Notice> findAllBySearch(String searchString) {
        ArrayList<Notice> notices = noticeDao.selectAllBySearch(searchString);
        return notices;
    }
}
