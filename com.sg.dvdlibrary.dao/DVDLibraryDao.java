/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.util.List;

/**
 *
 * @author junha
 */
public interface DVDLibraryDao {

    DVD addDVD(String dvdTitle, DVD dvd)
     throws DVDLibraryDaoException;

    List<DVD> getAllDVDs()
     throws DVDLibraryDaoException;

    DVD getDVD(String dvdTitle)
     throws DVDLibraryDaoException;
    
    DVD editDVD(String dvdTitle)
     throws DVDLibraryDaoException;

    DVD removeDVD(String dvdTitle)
     throws DVDLibraryDaoException;
}