"""
Dao - Data access object
Implementa a conexao com o
banco de dados.

"""
from contextlib import nullcontext
from database import Database
from entidades import Cliente, Produto

class ProdutoDao:
    # define cada funcionalidade do CRUD
    # CREATE
    def save(self, produto):
        """cria um novo registro no BD"""
        conn = Database.get_connection()
        conn.execute(
            f"""
            INSERT INTO produto (nome, preco, marca)
            VALUES (?, ?, ?)
            """,
            (
                produto.nome,  
                produto.preco, 
                produto.marca                 
            )
        )
        conn.commit()
        conn.close()

    # READ
    def find_all(self):
        """le todos os produtos tabela do BD"""
        conn = Database.get_connection()
        res = conn.execute("""
        SELECT id, nome, preco, marca, added FROM produto
        """
        )
        # executa o SELECT
        results = res.fetchall()
        # results eh um vetor
        results = [
            { 
                "id": produto[0], 
                "nome": produto[1],
                "preco": produto[2],
                "marca": produto[3],
                "added": produto[4],                
            } for produto in results]

        conn.close()
        return results
    
    def find_all_favoritos(self):
        """Alterar a tabela para conter o ID do cliente + produto
           Usar o inner join para consultar os favoritos com base no id do cliente
           sintaxe:         
           SELECT nome, marca FROM produto x INNER JOIN favoritos w on x.produto_id = w.id WHERE cliente_id = 1; 
        """
        conn = Database.get_connection()
        res = conn.execute("""
        SELECT p.id, nome, preco, marca FROM produto p INNER JOIN favorito f on f.produto_id = p.id  WHERE f.cliente_id = 1 
        """
        )
        # executa o SELECT
        results = res.fetchall()
        # results eh um vetor
        results = [
            {   "id": produto[0],               
                "nome": produto[1],
                "preco": produto[2],
                "marca": produto[3],                                
            } for produto in results]

        conn.close()
        return results

    def save_favorito(self, produto, clienteId):
        #USAR O LÓGICA DA LINHA 123 PARA CRIAR UM OBJETO
        conn = Database.get_connection()      

        busca = conn.execute(f"""
            SELECT produto_id FROM favorito WHERE produto_id = "{produto.id}"
        """
        )
        conn.commit()
        
        resultado = busca.fetchone()
               
        #Essa comparação não funciona...tratar o dado antes de comparar?
        # O SQL retorna como NULL dentro do banco, mais o PY recebe
        # como um SQL.Cursor  
        if resultado == None:
            conn.execute(
                f"""
                INSERT INTO favorito (cliente_id, produto_id)
                VALUES (?, ?)
                """,
                (
                        clienteId,  
                        produto.id                 
                )
            )
            conn.commit()
            conn.close()    
        else:     
        
            conn.close()

    def delete_favorito(self, id):
        
        conn = Database.get_connection()
        conn.execute(
            
            f"""
            DELETE FROM favorito WHERE produto_id = "{id}"
            """
        )
        conn.commit()
        conn.close()
    
    def busca_produto(self, busca):
        conn = Database.get_connection()
        res = conn.execute(f"""
        SELECT * FROM produto
        WHERE nome LIKE "%{busca}%"
        """
        )
        results = res.fetchall()
        results = [
            { 
                "id": produto[0], 
                "nome": produto[1],
                "preco": produto[2],
                "marca": produto[3],
                "added": produto[4],                
            } for produto in results]

        conn.close()
        return results

    def get_produto(self, id):
        conn = Database.get_connection()
        res = conn.execute(f"""
        SELECT id, nome, preco, marca, added FROM produto WHERE id = {id}
        """
        )
        row = res.fetchone()
        
        # cria um objeto cliente para armazenar resultado do SELECT:
        produto = Produto( 
            row[1],
            row[2],
            row[3],
            added = row[4],
            id = row[0]            
        )
        conn.close()
        return produto
    
    def get_busca(self, busca):
        conn = Database.get_connection()
        res = conn.execute(f"""
        SELECT id, nome, preco, marca, added FROM produto WHERE busca = {busca}
        """
        )
        row = res.fetchone()
        
        # cria um objeto cliente para armazenar resultado do SELECT:
        produto = Produto( 
            row[1],
            row[2],
            row[3],
            added = row[4],
            id = row[0]            
        )
        conn.close()
        return produto



    def delete(self, id):
        
        conn = Database.get_connection()
        conn.execute(
            
            f"""
            DELETE FROM produto WHERE id = {id}
            """
        )
        conn.commit()
        conn.close()

    def update(self, produto):
        
        conn = Database.get_connection()
        conn.execute(
            f"""
            UPDATE produto SET nome = ?, preco = ?, marca = ?
            WHERE id = ?
            """,
            (
                produto.nome,
                produto.preco,
                produto.marca,
                produto.id
            )
        )
        conn.commit()
        conn.close()

class ClienteDao:

    def save(self, cliente):
        """
        Realiza o INSERT na tabela cliente
        """
        # obtem uma conexao com o banco:
        conn = Database.get_connection()
        conn.execute(
            f"""
            INSERT INTO cliente (
                nome, cpf, cep, email            
            ) VALUES (?, ?, ?, ?)
            """,
            (
                cliente.nome,  
                cliente.cpf, 
                cliente.cep,
                cliente.email, 
            )
        )
        conn.commit()
        conn.close()


    def update(self, cliente):
        """
        Realiza UPDATE do cliente
        """
        conn = Database.get_connection()
        conn.execute(
            f"""
            UPDATE cliente SET nome = ?, cpf = ?, cep = ?, email = ?
            WHERE id = ?
            """,
            (
                cliente.nome,
                cliente.cpf,
                cliente.cep,
                cliente.email,
                cliente.id
            )
        )
        conn.commit()
        conn.close()

    def delete(self, id):
        """
        Remove um cliente de acordo com o id fornecido
        """
        conn = Database.get_connection()
        conn.execute(
            # Query
            # Hard Delete (cuidado!)
            f"""
            DELETE FROM cliente WHERE id = {id}
            """
        )
        conn.commit()
        conn.close()


    def find_all(self):
        conn = Database.get_connection()
        res = conn.execute("""
        SELECT id, nome, cpf, cep, email, data_cadastro FROM cliente
        """
        )
        # executa o SELECT
        results = res.fetchall()
        # results eh um vetor

        # versao 1
        results = [
            { 
                "id": cliente[0], 
                "nome": cliente[1],
                "cpf": cliente[2],
                "cep": cliente[3],
                "email": cliente[4],
                "data_cadastro": cliente[5],
            } for cliente in results]

        conn.close()
        return results


    def get_cliente(self, id):
        conn = Database.get_connection()
        res = conn.execute(f"""
        SELECT id, nome, email, cpf, cep, data_cadastro  FROM cliente WHERE id = {id}
        """
        )
        row = res.fetchone()
        
        # cria um objeto cliente para armazenar resultado do SELECT:
        cliente = Cliente( 
            row[1],
            row[2],
            id = row[0],
            cpf = row[3],
            cep = row[4],             
            data_cadastro = row[5]
        )
        conn.close()
        return cliente






if __name__ == "__main__":

    from dao import ProdutoDao
    from entidades import Produto

    dao = ProdutoDao()


    busca = Produto(dao.get_busca("LG"))

    print(busca)