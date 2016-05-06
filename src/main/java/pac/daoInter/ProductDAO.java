package pac.daoInter;

import pac.entities.Product;

/**
 * Created by macbookair on 18.04.16.
 */
public interface ProductDAO {
//    void add(Product product);
    void delete(Product product);
    void update(Product product);
    Product find(String name, String codeOfModel, String refPhoto);
}
