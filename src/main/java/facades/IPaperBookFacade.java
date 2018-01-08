package facades;

import entities.PaperBook;

public interface IPaperBookFacade {

    PaperBook addPaperBook(PaperBook pb);

    PaperBook getPaperBook(String isbn);

    PaperBook editPaperBook(PaperBook pb);

    PaperBook deletePaperBook(PaperBook pb);

    PaperBook deletePaperBook(String isbn);

}
