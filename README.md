# java-com-tesseract

Projeto Java com o uso do Tesseract para conclusão da cadeira de Linguagem de Programação III  - IFMA - Monte Castelo

Este projeto utiliza o processamento de imagens para identificar a placa de um carro a partir de um imagem 
(busca de imagem no computador ou direto da camera do notebook) e transformá-lo em string.

O objetivo é simular um sistema que permite ou não a entrada de pessoas em um condomínio dado a leitura da placa do carro.

PACOTES:

com.lp3.infra: Possui a classe ConnectioFactory que é responsável pela conexão da aplicação com o banco de dados MySQL.

com.lp3.dao: Possui a classe VeiculoDAO que é responsavel por fazer a busca no banco pela placa do carro. (Utilizou-se o JDBC)

com.lp3.util: 
Possui 3 classes: 
1- Classe Arquivo que é responsável por criar uma janela de busca da imagem pelo computador.
2 - Classe Conversor que é responsável por utilizar o Tesseract, para mais informações: http://tess4j.sourceforge.net/
3 - Classe TrataString é responsável por tirar caracteres especiais da string deixando no formato AAA0000

com.lp3.view: Possui a classe Principal que utiliza como interface o Java Swing.


