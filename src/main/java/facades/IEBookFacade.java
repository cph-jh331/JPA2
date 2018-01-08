/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EBook;

/**
 *
 * @author Bloch
 */
public interface IEBookFacade {

    EBook addEBook(EBook eb);

    EBook GetEBook(String isbn);

    EBook editEBook(EBook eb);

    EBook deleteEBook(EBook eb);

    EBook deleteEBook(String isbn);

}
