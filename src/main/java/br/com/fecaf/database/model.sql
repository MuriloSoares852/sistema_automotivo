-- ========================================
-- SISTEMA AUTOMOTIVO - GESTÃO DE VEÍCULOS
-- ========================================

-- 1. CRIAR E USAR O BANCO DE DADOS
-- ========================================
CREATE DATABASE sistema_automotivo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sistema_automotivo;

-- 2. CRIAR TABELAS
-- ========================================

-- Tabela: MARCA
-- Armazena as marcas dos veículos (Toyota, Honda, Ford, etc.)
CREATE TABLE marca (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_marca_nome CHECK (nome <> '')
) ENGINE=InnoDB;

-- Tabela: MODELO
-- Armazena os modelos de veículos relacionados às marcas
CREATE TABLE modelo (
    id_modelo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_marca INT NOT NULL,
    CONSTRAINT fk_modelo_marca FOREIGN KEY (id_marca)
        REFERENCES marca(id_marca)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT chk_modelo_nome CHECK (nome <> '')
) ENGINE=InnoDB;

-- Índice para melhorar consultas por marca
CREATE INDEX idx_modelo_marca ON modelo(id_marca);

-- Tabela: VEICULO
-- Armazena os veículos em estoque com todas as informações
CREATE TABLE veiculo (
    id_veiculo INT AUTO_INCREMENT PRIMARY KEY,
    id_modelo INT NOT NULL,
    ano INT NOT NULL,
    cor VARCHAR(50),
    preco DECIMAL(10,2) NOT NULL,
    quilometragem INT DEFAULT 0,
    status VARCHAR(20) DEFAULT 'Disponível',
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_veiculo_modelo FOREIGN KEY (id_modelo)
        REFERENCES modelo(id_modelo)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT chk_veiculo_ano CHECK (ano >= 1900 AND ano <= YEAR(CURDATE()) + 1),
    CONSTRAINT chk_veiculo_preco CHECK (preco > 0),
    CONSTRAINT chk_veiculo_km CHECK (quilometragem >= 0),
    CONSTRAINT chk_veiculo_status CHECK (status IN ('Disponível', 'Vendido', 'Reservado', 'Manutenção'))
) ENGINE=InnoDB;

-- Índices para melhorar desempenho nas consultas
CREATE INDEX idx_veiculo_modelo ON veiculo(id_modelo);
CREATE INDEX idx_veiculo_status ON veiculo(status);
CREATE INDEX idx_veiculo_ano ON veiculo(ano);
CREATE INDEX idx_veiculo_preco ON veiculo(preco);

-- 3. INSERIR DADOS DE TESTE
-- ========================================

-- Inserir Marcas
INSERT INTO marca (nome) VALUES
('Toyota'),
('Honda'),
('Ford'),
('Volkswagen'),
('Chevrolet'),
('Fiat'),
('Hyundai'),
('Nissan'),
('Jeep'),
('Renault');

-- Inserir Modelos
INSERT INTO modelo (nome, id_marca) VALUES
-- Toyota (id_marca = 1)
('Corolla', 1),
('Hilux', 1),
('Etios', 1),
('SW4', 1),

-- Honda (id_marca = 2)
('Civic', 2),
('HR-V', 2),
('City', 2),
('Fit', 2),

-- Ford (id_marca = 3)
('Fiesta', 3),
('Ka', 3),
('Ranger', 3),
('EcoSport', 3),

-- Volkswagen (id_marca = 4)
('Gol', 4),
('Polo', 4),
('T-Cross', 4),
('Virtus', 4),

-- Chevrolet (id_marca = 5)
('Onix', 5),
('Tracker', 5),
('S10', 5),
('Cruze', 5),

-- Fiat (id_marca = 6)
('Argo', 6),
('Mobi', 6),
('Toro', 6),
('Pulse', 6),

-- Hyundai (id_marca = 7)
('HB20', 7),
('Creta', 7),
('Tucson', 7),

-- Nissan (id_marca = 8)
('Kicks', 8),
('Versa', 8),
('Frontier', 8),

-- Jeep (id_marca = 9)
('Renegade', 9),
('Compass', 9),

-- Renault (id_marca = 10)
('Kwid', 10),
('Duster', 10);

-- Inserir Veículos
INSERT INTO veiculo (id_modelo, ano, cor, preco, quilometragem, status) VALUES
-- DISPONÍVEIS
(1, 2023, 'Prata', 125000.00, 15000, 'Disponível'),
(1, 2022, 'Branco', 110000.00, 28000, 'Disponível'),
(2, 2023, 'Branca', 195000.00, 12000, 'Disponível'),
(5, 2022, 'Preto', 115000.00, 20000, 'Disponível'),
(6, 2023, 'Vermelho', 98000.00, 8000, 'Disponível'),
(9, 2020, 'Branco', 58000.00, 65000, 'Disponível'),
(13, 2023, 'Azul', 82000.00, 5000, 'Disponível'),
(14, 2022, 'Cinza', 76000.00, 32000, 'Disponível'),
(17, 2023, 'Branco', 88000.00, 10000, 'Disponível'),
(18, 2022, 'Prata', 95000.00, 18000, 'Disponível'),
(21, 2023, 'Preto', 75000.00, 7000, 'Disponível'),
(25, 2022, 'Vermelho', 72000.00, 25000, 'Disponível'),
(26, 2023, 'Branco', 92000.00, 5000, 'Disponível'),
(29, 2023, 'Prata', 86000.00, 9000, 'Disponível'),

-- VENDIDOS
(3, 2020, 'Preto', 62000.00, 48000, 'Vendido'),
(7, 2019, 'Branco', 55000.00, 58000, 'Vendido'),
(10, 2018, 'Vermelho', 45000.00, 85000, 'Vendido'),
(15, 2021, 'Azul', 78000.00, 35000, 'Vendido'),
(19, 2020, 'Prata', 88000.00, 42000, 'Vendido'),
(22, 2019, 'Branco', 65000.00, 55000, 'Vendido'),

-- RESERVADOS
(4, 2023, 'Preta', 215000.00, 3000, 'Reservado'),
(11, 2023, 'Prata', 155000.00, 8000, 'Reservado'),
(20, 2023, 'Branco', 105000.00, 6000, 'Reservado'),
(30, 2023, 'Azul', 94000.00, 4000, 'Reservado'),

-- MANUTENÇÃO
(8, 2021, 'Cinza', 68000.00, 38000, 'Manutenção'),
(16, 2022, 'Preto', 89000.00, 22000, 'Manutenção');

-- 4. CONSULTAS ÚTEIS (EXEMPLOS)
-- ========================================

-- Listar todos os veículos com marca e modelo
SELECT
    v.id_veiculo,
    ma.nome AS marca,
    mo.nome AS modelo,
    v.ano,
    v.cor,
    CONCAT('R$ ', FORMAT(v.preco, 2, 'pt_BR')) AS preco,
    v.quilometragem AS km,
    v.status
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
ORDER BY ma.nome, mo.nome;

-- Filtrar veículos disponíveis
SELECT
    ma.nome AS marca,
    mo.nome AS modelo,
    v.ano,
    v.cor,
    v.preco,
    v.quilometragem
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
WHERE v.status = 'Disponível'
ORDER BY v.preco;

-- Filtrar por marca específica
SELECT
    mo.nome AS modelo,
    v.ano,
    v.cor,
    v.preco,
    v.status
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
WHERE ma.nome = 'Toyota'
ORDER BY v.ano DESC;

-- Filtrar por faixa de preço
SELECT
    ma.nome AS marca,
    mo.nome AS modelo,
    v.ano,
    v.preco,
    v.status
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
WHERE v.preco BETWEEN 50000 AND 100000
  AND v.status = 'Disponível'
ORDER BY v.preco;

-- Filtrar por ano
SELECT
    ma.nome AS marca,
    mo.nome AS modelo,
    v.cor,
    v.preco,
    v.quilometragem
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
WHERE v.ano = 2023
  AND v.status = 'Disponível'
ORDER BY v.preco;

-- Contar veículos por status
SELECT
    status,
    COUNT(*) AS quantidade,
    CONCAT('R$ ', FORMAT(SUM(preco), 2, 'pt_BR')) AS valor_total
FROM veiculo
GROUP BY status;

-- Contar veículos por marca
SELECT
    ma.nome AS marca,
    COUNT(v.id_veiculo) AS total_veiculos,
    SUM(CASE WHEN v.status = 'Disponível' THEN 1 ELSE 0 END) AS disponiveis,
    SUM(CASE WHEN v.status = 'Vendido' THEN 1 ELSE 0 END) AS vendidos
FROM marca ma
LEFT JOIN modelo mo ON ma.id_marca = mo.id_marca
LEFT JOIN veiculo v ON mo.id_modelo = v.id_modelo
GROUP BY ma.id_marca, ma.nome
ORDER BY total_veiculos DESC;

-- 5. OPERAÇÕES DE ATUALIZAÇÃO
-- ========================================

-- Atualizar preço de um veículo
UPDATE veiculo
SET preco = 120000.00
WHERE id_veiculo = 1;

-- Atualizar quilometragem
UPDATE veiculo
SET quilometragem = 30000
WHERE id_veiculo = 1;

-- Atualizar status (marcar como vendido)
UPDATE veiculo
SET status = 'Vendido'
WHERE id_veiculo = 1;

-- Atualizar múltiplos campos
UPDATE veiculo
SET preco = 118000.00,
    quilometragem = 32000,
    status = 'Disponível'
WHERE id_veiculo = 2;

-- 6. OPERAÇÕES DE EXCLUSÃO
-- ========================================

-- Remover veículo específico
DELETE FROM veiculo WHERE id_veiculo = 1;

-- Remover todos os veículos vendidos (CUIDADO!)
-- DELETE FROM veiculo WHERE status = 'Vendido';

-- Remover modelo (remove automaticamente os veículos daquele modelo)
-- DELETE FROM modelo WHERE id_modelo = 1;

-- 7. VIEWS ÚTEIS (OPCIONAL)
-- ========================================

-- View de veículos disponíveis
CREATE OR REPLACE VIEW v_veiculos_disponiveis AS
SELECT
    v.id_veiculo,
    ma.nome AS marca,
    mo.nome AS modelo,
    v.ano,
    v.cor,
    v.preco,
    v.quilometragem,
    v.data_cadastro
FROM veiculo v
INNER JOIN modelo mo ON v.id_modelo = mo.id_modelo
INNER JOIN marca ma ON mo.id_marca = ma.id_marca
WHERE v.status = 'Disponível'
ORDER BY v.data_cadastro DESC;

-- View de estatísticas por marca
CREATE OR REPLACE VIEW v_estatisticas_marca AS
SELECT
    ma.nome AS marca,
    COUNT(v.id_veiculo) AS total_veiculos,
    AVG(v.preco) AS preco_medio,
    MIN(v.preco) AS preco_minimo,
    MAX(v.preco) AS preco_maximo,
    SUM(CASE WHEN v.status = 'Disponível' THEN 1 ELSE 0 END) AS disponiveis
FROM marca ma
LEFT JOIN modelo mo ON ma.id_marca = mo.id_marca
LEFT JOIN veiculo v ON mo.id_modelo = v.id_modelo
GROUP BY ma.id_marca, ma.nome;

-- 8. VERIFICAÇÕES FINAIS
-- ========================================

-- Ver todas as tabelas criadas
SHOW TABLES;

-- Ver estrutura das tabelas
DESCRIBE marca;
DESCRIBE modelo;
DESCRIBE veiculo;

-- Contar registros
SELECT 'Marcas' AS tabela, COUNT(*) AS total FROM marca
UNION ALL
SELECT 'Modelos', COUNT(*) FROM modelo
UNION ALL
SELECT 'Veículos', COUNT(*) FROM veiculo;

-- Verificar relacionamentos
SELECT
    ma.nome AS marca,
    COUNT(DISTINCT mo.id_modelo) AS total_modelos,
    COUNT(v.id_veiculo) AS total_veiculos
FROM marca ma
LEFT JOIN modelo mo ON ma.id_marca = mo.id_marca
LEFT JOIN veiculo v ON mo.id_modelo = v.id_modelo
GROUP BY ma.id_marca, ma.nome
ORDER BY total_veiculos DESC;

-- ========================================
-- FIM DO SCRIPT
-- ========================================