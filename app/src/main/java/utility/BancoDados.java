package utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import models.CheckLists;
import models.GrupoItemVistoria;
import models.ItensCheckList;
import models.ItensVistoria;
import models.LocalizacaoItemVistoria;
import models.ModelosCheckList;
import models.RelModeloCheckListImagemEsquema;
import models.RelModeloCheckListItensVistoria;
import models.RelModeloCheckListVeiculos;
import models.RelPermissaoPessoasApoioCheckListVeiculos;
import models.UsuarioPessoasApoio;
import models.Usuarios;
import models.Veiculos;

/**
 * Created by William on 09/01/17.
 * Classe java para a
 */

public class BancoDados extends SQLiteOpenHelper {

	// Logcat tag

	private static final String LOG = "BancoDados";

	// Database Version
	private static final int DATABASE_VERSION = 6;

	// Database Name
	private static final String DATABASE_NAME = "darwin_tablet.db";

	// Table Names
	private static final String T_LOCALIZACAO_ITEM_VISTORIA = "t_localizacao_item_vistoria";
	private static final String T_ITENS_VISTORIA = "t_itens_vistoria";
	private static final String T_GRUPO_ITEM_VISTORIA = "t_grupo_item_vistoria";
	private static final String T_USUARIOS_PESSOAS_APOIO = "t_usuarios_pessoas_apoio";
	private static final String T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS = "t_rel_permissao_pessoas_apoio_check_list_veiculos";
	private static final String T_IMAGENS_ESQUEMA_CHECK_LIST = "t_imagens_esquema_check_list";
	private static final String T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA = "t_rel_modelo_check_list_itens_vistoria";
	private static final String T_REL_MODELO_CHECK_LIST_VEICULOS = "t_rel_modelo_check_list_veiculos";
	private static final String T_MODELOS_CHECK_LIST = "t_modelos_check_list";
	private static final String T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA = "t_rel_modelo_check_list_imagem_esquema";
	private static final String T_CHECK_LISTS = "t_check_lists";
	private static final String T_ANALISE_ITENS_CHECK_LIST = "t_analise_itens_check_list";
	private static final String T_VEICULOS = "t_veiculos";
	private static final String T_USUARIOS = "t_users";

	// Inicio das definicoes de colunas de tabelas
	/*****************************************************************************************************************************************/
	//Colunas comuns para algumas tabelas
	private static final String COD = "cod";
	private static final String NOME = "nome";
	private static final String FLG_ATIVO = "flg_ativo";
	private static final String DATA_CADASTRO = "data_cadastro";
	private static final String COD_USUARIO_CLIENTE_RESP = "cod_usuario_cliente_resp";
	private static final String COD_USUARIO_RESP = "cod_usuario_resp";
	private static final String DATA_ACAO = "data_acao";
	private static final String CODIGO_ITEM = "codigo_item";
	private static final String CODIGO_IMAGEM = "codigo_imagem";
	private static final String DESCRICAO_IMAGEM = "descricao_imagem_check_list";
	private static final String COD_MODELO_CHECK_LIST = "cod_modelo_check_list";
	private static final String COD_ITEM_VISTORIA = "cod_item_vistoria";
	private static final String COD_VEICULO = "cod_veiculo";
	private static final String COD_VEICULO_W = "cod_veiculo_w";
	private static final String PLACA_VEICULO = "placa_veiculo";
	private static final String COD_PESSOA_APOIO = "cod_pessoa_apoio";


	/*****************************************************************************************************************************************/
	//Colunas da tabela tLocalizacaoItemVistoria
	//campo COD
	//campo NOME
	private static final String CAMINHO_ARQUIVO_IMAGEM_ILUSTRACAO = "caminho_arquivo_imagem_ilustracao";
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP
	//campo DATA_ACAO

	/*****************************************************************************************************************************************/
	//Colunas da tabela tItensVistoria

	//campo COD
	//campo CODIGO_ITEM
	private static final String NOME_ITEM = "nome_item";
	private static final String COD_LOCALIZACAO_ITEM = "cod_localizacao_item";
	private static final String COD_GRUPO_ITEM = "cod_grupo_item";
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP
	//campo DATA_ACAO

	/*****************************************************************************************************************************************/
	//Colunas da tabela tGrupoItemVistoria

	//campo COD
	private static final String FLG_GRUPO_REBOQUE = "flg_grupo_reboque";
	private static final String FLG_GRUPO_CAVALO = "flg_grupo_cavalo";
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo DATA_ACAO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	/*****************************************************************************************************************************************/
	//Colunas da tabela tUsuariosPessoasApoio

	//campo COD
	//campo COD_PESSOA_APOIO
	private static final String USUARIO = "usuario";
	//private static final String SENHA = "senha";
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP
	//campo DATA_ACAO

	/*****************************************************************************************************************************************/
	//Colunas da tabela tRelPermissaoPessoasApoioCheckListVeiculos

	//campo COD
	//campo COD_PESSOA_APOIO
	private static final String COD_USUARIO_PESSOA_APOIO = "cod_usuario_pessoa_apoio";
	//campo COD_VEICULO_W;
	//campo COD_VEICULO
	//campo FLG_ATIVO;
	//campo COD_USUARIO_CLIENTE_RESP;
	//campo COD_USUARIO_RESP;
	//campo DATA_ACAO;

	/*****************************************************************************************************************************************/
	//Colunas da tabela tImagemEsquemaCheckList

	//campo COD
	//campo CODIGO_IMAGEM
	private static final String CAMINHO_ARQUIVO_IMAGEM = "caminho_arquivo_imagem";
	//campo DESCRICAO_IMAGEM
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP
	//campo DATA_ACAO

	/*****************************************************************************************************************************************/
	//Colunas da tabela tRelModeloListItensVistoria

	//campo COD
	//campo codModeloCheckList
	//campo COD_ITEM_VISTORIA
	//campo CODIGO_ITEM
	private static final String COD_GRUPO_ITEM_VISTORIA = "cod_grupo_item_vistoria";
	private static final String COD_LOCALIZACAO_ITEM_VISTORIA = "cod_localizacao_item_vistoria";
	private static final String FLG_OPCAO_OK = "flg_opcao_ok";
	private static final String FLG_OPCAO_NAO_OK = "flg_opcao_nao_ok";
	private static final String FLG_OPCAO_NAO_EFETUADO = "flg_opcao_nao_efetuado";
	private static final String FLG_OPCAO_OBSERVACAO = "flg_opcao_observacao";
	private static final String FLG_OPCAO_ANEXO = "flg_opcao_anexo";
	private static final String ORDEM_SEQUENCIA_VISTORIA = "ordem_sequencia_vistoria";
	private static final String MENSAGEM_EXECUTOR_VISTORIA_ITEM = "mensagem_executor_vistoria_item";
	//campo FLG_ATIVO
	//campo DATA_ACAO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	/***************************************************************************************************************************************/
	//Colunas da tabela tRelModeloCheckListVeiculos

	//campo COD
	//campo COD_VEICULO
	//campo COD_VEICULO_W
	//campo placa veiculo
	//campo codModeloCheckList
	//campo FLG_ATIVO
	//campo DATA_ACAO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	/*****************************************************************************************************************************************/
	//Colunas da tabela tModelosCheckList

	//campo COD
	//campo NOME
	private static final String DESCRICAO = "descricao";
	private static final String MENSAGENS_GERAIS_EXECUTOR_CHECK_LIST = "mensagens_gerais_executor_check_list";
	//campo DATA_ACAO
	private static final String FLG_SOMENTE_MOTORISTA_ATUAL_EXECUTA = "flg_somente_motorista_atual_executa";
	private static final String FLG_SOMENTE_GERENTES_OPERACAO_EXECUTAM = "flg_somente_gerentes_operacao_executam";
	private static final String FLG_SOMENTE_PESSOA_APOIO_EXECUTAM = "flg_somente_pessoa_apoio_executam";
	private static final String FLG_TODAS_PESSOAs_EXECUTAM = "flg_todas_pessoas_executam";
	private static final String TIPO_DISPONIBILIDADE_MODELO = "tipo_disponibilidade_modelo";
	//campo FLG_ATIVO
	//campo DATA_CADASTRO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	/*****************************************************************************************************************************************/
	//Colunas da tabela tRelModeloCheckListImagemEsquema

	//campo COD
	//campo codModeloCheckList
	private static final String COD_IMAGEM_ESQUEMA_CHECK_LIST = "cod_imagem_esquema_check_list";
	//campo CODIGO_IMAGEM
	//campo DESCRICAO_IMAGEM
	//campo FLG_ATIVO
	//campo DATA_ACAO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	/*****************************************************************************************************************************************/
	//Colunas da tabela tUSUARIOS

	//campo COD
	private static final String COD_DARWIN = "cod_darwin";
	private static final String COD_CLIENTE = "cod_cliente";
	private static final String RAZAO_SOCIAL = "razao_social";
	private static final String CAMINHO_LOGO = "caminho_logo";
	private static final String TIPO_USUARIO = "tipo_usuario";
	private static final String LOGIN = "login";
	private static final String SENHA = "senha";
	/*****************************************************************************************************************************************/
	//Colunas da tabela tCheckLists

	//campo COD
	//campo COD_VEICULO
	//campo codModeloCheckList
	//campo COD_VEICULO_W
	//campo placa veiculo
	private static final String TIPO_SELECAO_VEICULO = "tipo_selecao_veiculo";
	private static final String PLACAS_CARRETAS = "placas_carretas";
	private static final String FLG_SOMENTE_REBOQUES = "flg_somente_reboques";
	private static final String RFID_CARRETAS = "rfid_carretas";
	private static final String IDENTIFICADOR_DIGITAL_ULTIMO_MOTORISTA_VEICULO = "identificador_digital_ultimo_motorista_veiculo";
	private static final String IDENTIFICADOR_DIGITAL_MOTORISTA_ATUAL = "identificador_digital_motorista_atual";
	private static final String IDENTIFICADOR_DIGITAL_PESSOA_APOIO_ATUAL = "identificador_digital_pessoa_apoio_atual";
	private static final String IDENTIFICADOR_DIGITAL_MOTORISTA_VISTORIA = "identidicado_digital_motorista_vistoria";
	private static final String COD_USUARIO_CLIENTE_VISTORIA = "cod_usuario_cliente_vistoria";
	private static final String COD_PESSOA_APOIO_VISTORIA = "cod_pessoa_apoio_vistoria";
	private static final String COD_USUARIO_TECHNOLOG_VISTORIA = "cod_usuario_technolog_vistoria";
	private static final String DATA_INICIO_VISTORIA = "data_inicio_vistoria";
	private static final String DATA_FIM_VISTORIA = "data_fim_vistoria";
	private static final String DATA_STATUS_PREENCHIMENTO = "data_status_preenchimento";
	private static final String STATUS_PREENCHIMENTO = "status_preenchimento";
	private static final String STATUS_CHECK_LIST = "status_check_list";
	private static final String COMENTARIOS = "comentarios";
	private static final String NUMERO_CONHECIMENTO_TRANSPORTE = "numero_conhecimento_transporte";
	private static final String NUMERO_MANIFESTO = "numero_manifesto";
	private static final String IDENTIFICADOR_SISTEMAS_TERCEIROS = "identificador_sistemas_terceiros";
	private static final String VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA = "volume_combustivel_tanques_inicio_vistoria";
	private static final String STATUS_TANQUES_VEICULOS_INICIO_VISTORIA = "status_tanques_veiculo_inicio_vistoria";
	private static final String KM_VEICULO_INICIO_VISTORIA = "km_Veiculo_Inicio_Vistoria";
	private static final String LATITUDE_VEICULO_INICIO_VISTORIA = "latirude_veiculo_inicio_vistoria";
	private static final String LONGITUDE_VEICULO_INICIO_VISTORIA = "longitude_veiculo_inicio_vistoria";
	private static final String VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA = "volume_combustivel_tanques_fim_vistoria";
	private static final String STATUS_TANQUES_VEICULO_FIM_VISTORIA = "status_tanques_veiculo_fim_vistoria";
	private static final String KM_VEICULOS_FIM_VISTORIA = "km_veiculos_fim_vistoria";
	private static final String LATITUDE_VEICULO_FIM_VISTORIA = "latitude_veiculo_fim_vistoria";
	private static final String LONGITUDE_VEICULO_FIM_VISTORIA = "longitude_veiculo_fim_vistoria";
	private static final String LATITUDE_DISPOSITIVO_CHECK_LIST = "latitude_dispositivo_check_list";
	private static final String LONGITUDE_DISPOSITIVO_CHECK_LIST = "longitude_dispositivo_check_list";
	private static final String CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR = "caminho_arquivo_imagem_assinatura_executor";
	private static final String CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO = "caminho_arquivo_foto_placa_veiculo";
	//campo FLG_ATIVO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP
	//campo DATA_ACAO
	private static final String TIPO_PREENCHIMENTO = "tipo_preenchimento";

	/*****************************************************************************************************************************************/
	//Colunas da tabela tAnaliseItensCheckList

	//campo COD
	private static final String COD_CHECK_LIST = "cod_check_list";
	//campo COD_ITEM_VISTORIA
	private static final String FLG_OK = "flg_ok";
	private static final String FLG_NAO_OK = "flg_nao_ok";
	private static final String FLG_NAO_EFETUADO = "flg_nao_efetuado";
	private static final String FLG_NECESSITA_TROCA_REPARO = "flg_necessita_troca_reparo";
	private static final String OBSERVACAO_EXECUTOR = "observacao_executor";
	private static final String ARQUIVO_ANEXO_CHECK_LIST = "caminho_arquivo_anexo_check_list";
	//campo FLG_ATIVO
	//campo DATA_ACAO
	//campo COD_USUARIO_CLIENTE_RESP
	//campo COD_USUARIO_RESP

	//t_veiculos
	//COD
	private static final String PLACA = "placa";
	/*****************************************************************************************************************************************/
	//Criação de tabelas
	//Criação da t_veiculos
	private static final String CREATE_T_VEICULOS =
			"CREATE TABLE " + T_VEICULOS + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
					COD_VEICULO_W + " INTEGER, " +
					PLACA + " VARCHAR(10) NOT NULL "
					+ ")";

	//Criação da tabela T_LOCALIZACAO_ITEM_VISTORIA
	private static final String CREATE_T_LOCALIZACAO_ITEM_VISTORIA =
			"CREATE TABLE " + T_LOCALIZACAO_ITEM_VISTORIA + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					NOME + " VARCHAR(500) NOT NULL," +
					CAMINHO_ARQUIVO_IMAGEM_ILUSTRACAO + " VARCHAR(5000)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_CADASTRO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME" +
					")";

	//Criação da tabela T_ITENS_VISTORIA
	private static final String CREATE_T_ITENS_VISTORIA =
			"CREATE TABLE " + T_ITENS_VISTORIA + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					CODIGO_ITEM + " VARCHAR(50)," +
					NOME_ITEM + " VARCHAR(50) NOT NULL," +
					COD_LOCALIZACAO_ITEM + " INTEGER," +
					COD_GRUPO_ITEM + " INTEGER," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_CADASTRO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME," +
					"FOREIGN KEY (" + COD_LOCALIZACAO_ITEM + ") REFERENCES " + T_LOCALIZACAO_ITEM_VISTORIA + "(" + COD + ")," +
					"FOREIGN KEY (" + COD_GRUPO_ITEM + ") REFERENCES " + T_GRUPO_ITEM_VISTORIA + "(" + COD + ")" +
					")";

	//Criação da tabela T_GRUPO_ITEM_VISTORIA
	private static final String CREATE_T_GRUPO_ITEM_VISTORIA =
			"CREATE TABLE " + T_GRUPO_ITEM_VISTORIA + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					NOME + " VARCHAR(500) NOT NULL," +
					FLG_GRUPO_CAVALO + " BOOLEAN," +
					FLG_GRUPO_REBOQUE + " BOOLEAN," +
					FLG_ATIVO + " BOOLEAN," +
					DATA_CADASTRO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME" +
					")";

	//Criação da tabela T_USUARIOS_PESSOAS_APOIO
	private static final String CREATE_T_USUARIOS_PESSOAS_APOIO =
			"CREATE TABLE " + T_USUARIOS_PESSOAS_APOIO + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_PESSOA_APOIO + " INTEGER NOT NULL," +
					USUARIO + " VARCHAR(50) NOT NULL," +
					SENHA + " VARCHAR(50) NOT NULL," +
					FLG_ATIVO + " BOOLEAN," +
					DATA_CADASTRO + " DATETIME," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME" +
					")";

	//Criação da tabela T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS
	private static final String CREATE_T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS =
			"CREATE TABLE " + T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_PESSOA_APOIO + " INTEGER NOT NULL," +
					COD_USUARIO_PESSOA_APOIO + " INTEGER," +
					COD_VEICULO_W + " INTEGER," +
					COD_VEICULO + " INTEGER," +
					FLG_ATIVO + " BOOLEAN," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME," +
					"FOREIGN KEY (" + COD_USUARIO_PESSOA_APOIO + ") REFERENCES " + T_USUARIOS_PESSOAS_APOIO + "(" + COD + ")" +
					")";

	//Criação da tabela T_IMAGENS_ESQUEMA_CHECK_LIST
	private static final String CREATE_T_IMAGENS_ESQUEMA_CHECK_LIST =
			"CREATE TABLE " + T_IMAGENS_ESQUEMA_CHECK_LIST + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					CODIGO_IMAGEM + " VARCHAR(50) NOT NULL," +
					CAMINHO_ARQUIVO_IMAGEM + " VARCHAR(500) NOT NULL," +
					DESCRICAO_IMAGEM + " VARCHAR(5000)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_CADASTRO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME " +
					")";

	//Criação da tabela T_REL_MODELO_LIST_ITENS_VISTORIA
	private static final String CREATE_T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA =
			"CREATE TABLE " + T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_MODELO_CHECK_LIST + " INTEGER NOT NULL," +
					COD_ITEM_VISTORIA + " INTEGER NOT NULL," +
					COD_LOCALIZACAO_ITEM_VISTORIA + " INTEGER," +
					CODIGO_ITEM + " VARCHAR(50)," +
					COD_GRUPO_ITEM_VISTORIA + " INTEGER," +
					FLG_OPCAO_OK + " BOOLEAN NOT NULL," +
					FLG_OPCAO_NAO_OK + " BOOLEAN NOT NULL," +
					FLG_OPCAO_NAO_EFETUADO + " BOOLEAN NOT NULL," +
					FLG_OPCAO_OBSERVACAO + " BOOLEAN NOT NULL," +
					FLG_OPCAO_ANEXO + " BOOLEAN NOT NULL," +
					ORDEM_SEQUENCIA_VISTORIA + " SMALLINT," +
					MENSAGEM_EXECUTOR_VISTORIA_ITEM + " VARCHAR(5000)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_ACAO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					"FOREIGN KEY (" + COD_MODELO_CHECK_LIST + ") REFERENCES " + T_MODELOS_CHECK_LIST + "(" + COD + ")," +
					"FOREIGN KEY (" + COD_ITEM_VISTORIA + ") REFERENCES " + T_ITENS_VISTORIA + "(" + COD + ")" +
					")";

	//Criação da tabela T_REL_MODELO_CHECK_LIST_VEICULOS
	private static final String CREATE_T_REL_MODELO_CHECK_LIST_VEICULOS =
			"CREATE TABLE " + T_REL_MODELO_CHECK_LIST_VEICULOS + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_VEICULO + " INTEGER NOT NULL," +
					COD_VEICULO_W + " INTEGER NOT NULL," +
					PLACA_VEICULO + " VARCHAR(40)," +
					COD_MODELO_CHECK_LIST + " INTEGER NOT NULL," +
					FLG_ATIVO + " BOOLEAN," +
					DATA_ACAO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					"FOREIGN KEY (" + COD_MODELO_CHECK_LIST + ") REFERENCES " + T_MODELOS_CHECK_LIST + "(" + COD + ")" +
					")";

	//Criação da tabela T_MODELOS_CHECK_LIST
	private static final String CREATE_T_MODELOS_CHECK_LIST =
			"CREATE TABLE " + T_MODELOS_CHECK_LIST + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					NOME + " VARCHAR(500) NOT NULL," +
					DESCRICAO + " VARCHAR(5000)," +
					MENSAGENS_GERAIS_EXECUTOR_CHECK_LIST + " VARCHAR(5000)," +
					DATA_ACAO + " DATETIME," +
					FLG_SOMENTE_MOTORISTA_ATUAL_EXECUTA + " BOOLEAN NOT NULL," +
					FLG_SOMENTE_GERENTES_OPERACAO_EXECUTAM + " BOOLEAN NOT NULL," +
					FLG_SOMENTE_PESSOA_APOIO_EXECUTAM + " BOOLEAN," +
					FLG_TODAS_PESSOAs_EXECUTAM + " BOOLEAN NOT NULL," +
					TIPO_DISPONIBILIDADE_MODELO + " INTEGER," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_CADASTRO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER" +
					")";

	//Criação da tabela T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA
	private static final String CREATE_T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA =
			"CREATE TABLE " + T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_MODELO_CHECK_LIST + " INTEGER NOT NULL," +
					COD_IMAGEM_ESQUEMA_CHECK_LIST + " INTEGER NOT NULL," +
					CODIGO_IMAGEM + " VARCHAR(50) NOT NULL," +
					DESCRICAO_IMAGEM + " VARCHAR(500)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_ACAO + " DATETIME," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					"FOREIGN KEY (" + COD_MODELO_CHECK_LIST + ") REFERENCES " + T_MODELOS_CHECK_LIST + "(" + COD + ")," +
					"FOREIGN KEY (" + COD_IMAGEM_ESQUEMA_CHECK_LIST + ") REFERENCES " + T_IMAGENS_ESQUEMA_CHECK_LIST + "(" + COD + ")" +
					")";

	//Criação da tabela T_CHECK_LISTS
	private static final String CREATE_T_CHECK_LISTS =
			"CREATE TABLE " + T_CHECK_LISTS + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_VEICULO + " INTEGER NOT NULL," +
					COD_MODELO_CHECK_LIST + " INTEGER NOT NULL," +
					COD_VEICULO_W + " INTEGER," +
					PLACA_VEICULO + " VARCHAR(40)," +
					TIPO_SELECAO_VEICULO + " SMALLINT," +
					PLACAS_CARRETAS + " VARCHAR(200)," +
					FLG_SOMENTE_REBOQUES + " BOOLEAN," +
					RFID_CARRETAS + " VARCHAR(200)," +
					IDENTIFICADOR_DIGITAL_ULTIMO_MOTORISTA_VEICULO + " VARCHAR(500)," +
					IDENTIFICADOR_DIGITAL_MOTORISTA_ATUAL + " VARCHAR(500)," +
					IDENTIFICADOR_DIGITAL_PESSOA_APOIO_ATUAL + " VARCHAR(500)," +
					IDENTIFICADOR_DIGITAL_MOTORISTA_VISTORIA + " VARCHAR(500)," +
					COD_USUARIO_CLIENTE_VISTORIA + " INTEGER," +
					COD_PESSOA_APOIO_VISTORIA + " INTEGER," +
					COD_USUARIO_TECHNOLOG_VISTORIA + " INTEGER," +
					DATA_INICIO_VISTORIA + " DATETIME," +
					DATA_FIM_VISTORIA + " DATETIME," +
					STATUS_PREENCHIMENTO + " SMALLINT," +
					DATA_STATUS_PREENCHIMENTO + " DATETIME," +
					STATUS_CHECK_LIST + " SMALLINT," +
					COMENTARIOS + " VARCHAR(5000)," +
					NUMERO_CONHECIMENTO_TRANSPORTE + " VARCHAR(50)," +
					NUMERO_MANIFESTO + " VARCHAR(50)," +
					IDENTIFICADOR_SISTEMAS_TERCEIROS + " VARCHAR(200)," +
					VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA + " REAL," +
					STATUS_TANQUES_VEICULOS_INICIO_VISTORIA + " REAL," +
					KM_VEICULO_INICIO_VISTORIA + " REAL," +
					LATITUDE_VEICULO_INICIO_VISTORIA + " VARCHAR(25)," +
					LONGITUDE_VEICULO_INICIO_VISTORIA + " VARCHAR(25)," +
					VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA + " REAL," +
					STATUS_TANQUES_VEICULO_FIM_VISTORIA + " REAL," +
					KM_VEICULOS_FIM_VISTORIA + " REAL," +
					LATITUDE_VEICULO_FIM_VISTORIA + " VARCHAR(25)," +
					LONGITUDE_VEICULO_FIM_VISTORIA + " VARCHAR(25)," +
					LATITUDE_DISPOSITIVO_CHECK_LIST + " VARCHAR(25)," +
					LONGITUDE_DISPOSITIVO_CHECK_LIST + " VARCHAR(25)," +
					CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR + " VARCHAR(500)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					DATA_ACAO + " DATETIME," +
					TIPO_PREENCHIMENTO + " SMALLINT," +
					CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO + " VARCHAR(500)," +
					"FOREIGN KEY (" + COD_MODELO_CHECK_LIST + ") REFERENCES " + T_MODELOS_CHECK_LIST + "(" + COD + ")" +
					")";

	//Criação da tabela T_ANALISE_ITENS_CHECK_LIST
	private static final String CREATE_T_ANALISE_ITENS_CHECK_LIST =
			"CREATE TABLE " + T_ANALISE_ITENS_CHECK_LIST + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_CHECK_LIST + " INTEGER NOT NULL," +
					COD_ITEM_VISTORIA + " INTEGER NOT NULL," +
					FLG_OK + " BOOLEAN NOT NULL," +
					FLG_NAO_OK + " BOOLEAN NOT NULL," +
					FLG_NAO_EFETUADO + " BOOLEAN NOT NULL," +
					FLG_NECESSITA_TROCA_REPARO + " BOOLEAN NOT NULL," +
					OBSERVACAO_EXECUTOR + " VARCHAR(5000)," +
					ARQUIVO_ANEXO_CHECK_LIST + " VARCHAR(500)," +
					FLG_ATIVO + " BOOLEAN NOT NULL," +
					DATA_ACAO + " DATETIME NOT NULL," +
					COD_USUARIO_CLIENTE_RESP + " INTEGER," +
					COD_USUARIO_RESP + " INTEGER," +
					"FOREIGN KEY (" + COD_ITEM_VISTORIA + ") REFERENCES " + T_ITENS_VISTORIA + "(" + COD + ")," +
					"FOREIGN KEY (" + COD_CHECK_LIST + ") REFERENCES " + T_CHECK_LISTS + "(" + COD + ")" +
					")";


	//Criação da tabela T_USUARIOS
	private static final String CREATE_T_USUARIOS =
			"CREATE TABLE " + T_USUARIOS + "(" +
					COD + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
					COD_DARWIN + " VARCHAR(50)," +
					LOGIN + " VARCHAR(50) NOT NULL," +
					SENHA + " VARCHAR(50)," +
					COD_CLIENTE + " INTEGER," +
					RAZAO_SOCIAL + " VARCHAR(50)," +
					CAMINHO_LOGO + " VARCHAR(50)," +
					TIPO_USUARIO + " INTEGER" +
					")";

	public BancoDados(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_T_LOCALIZACAO_ITEM_VISTORIA);
		db.execSQL(CREATE_T_GRUPO_ITEM_VISTORIA);
		db.execSQL(CREATE_T_ITENS_VISTORIA);
		db.execSQL(CREATE_T_USUARIOS_PESSOAS_APOIO);
		db.execSQL(CREATE_T_IMAGENS_ESQUEMA_CHECK_LIST);
		db.execSQL(CREATE_T_MODELOS_CHECK_LIST);
		db.execSQL(CREATE_T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA);
		db.execSQL(CREATE_T_CHECK_LISTS);
		db.execSQL(CREATE_T_ANALISE_ITENS_CHECK_LIST);
		db.execSQL(CREATE_T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS);
		db.execSQL(CREATE_T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA);
		db.execSQL(CREATE_T_REL_MODELO_CHECK_LIST_VEICULOS);
		db.execSQL(CREATE_T_VEICULOS);
		db.execSQL(CREATE_T_USUARIOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int i, int i1) {
		db.execSQL("DROP TABLE IF EXISTS " + T_LOCALIZACAO_ITEM_VISTORIA);
		db.execSQL("DROP TABLE IF EXISTS " + T_GRUPO_ITEM_VISTORIA);
		db.execSQL("DROP TABLE IF EXISTS " + T_ITENS_VISTORIA);
		db.execSQL("DROP TABLE IF EXISTS " + T_USUARIOS_PESSOAS_APOIO);
		db.execSQL("DROP TABLE IF EXISTS " + T_IMAGENS_ESQUEMA_CHECK_LIST);
		db.execSQL("DROP TABLE IF EXISTS " + T_MODELOS_CHECK_LIST);
		db.execSQL("DROP TABLE IF EXISTS " + T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA);
		db.execSQL("DROP TABLE IF EXISTS " + T_CHECK_LISTS);
		db.execSQL("DROP TABLE IF EXISTS " + T_ANALISE_ITENS_CHECK_LIST);
		db.execSQL("DROP TABLE IF EXISTS " + T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS);
		db.execSQL("DROP TABLE IF EXISTS " + T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA);
		db.execSQL("DROP TABLE IF EXISTS " + T_REL_MODELO_CHECK_LIST_VEICULOS);
		db.execSQL("DROP TABLE IF EXISTS " + T_VEICULOS);
		db.execSQL("DROP TABLE IF EXISTS " + T_USUARIOS);

		// on upgrade drop older table
		// create new tables
		onCreate(db);
	}

	public long insereCheckLists(CheckLists c) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, c.getCod());
		valores.put(COD_VEICULO, c.getCodVeiculo());
		valores.put(COD_MODELO_CHECK_LIST, c.getCodModeloCheckList());
		valores.put(COD_VEICULO_W, c.getCodVeiculoW());
		valores.put(PLACA_VEICULO, c.getPlacaVeiculo());
		valores.put(TIPO_SELECAO_VEICULO, c.getTipoSelecaoVeiculo());
		valores.put(PLACAS_CARRETAS, c.getPlacasCarretas());
		valores.put(FLG_SOMENTE_REBOQUES, c.getFlgSomenteReboques());
		valores.put(RFID_CARRETAS, c.getRfidCarretas());
		valores.put(IDENTIFICADOR_DIGITAL_ULTIMO_MOTORISTA_VEICULO, c.getIdentificadorDigitalUltimoMotoristaVeiculo());
		valores.put(IDENTIFICADOR_DIGITAL_MOTORISTA_ATUAL, c.getIdentificadorDigitalMotoristaAtual());
		valores.put(IDENTIFICADOR_DIGITAL_PESSOA_APOIO_ATUAL, c.getIdentificadorDigitalPessoaApoioAtual());
		valores.put(IDENTIFICADOR_DIGITAL_MOTORISTA_VISTORIA, c.getIdentificadorDigitalMotoristaVistoria());
		valores.put(COD_USUARIO_CLIENTE_VISTORIA, c.getCodUsuarioClienteVistoria());
		valores.put(COD_PESSOA_APOIO_VISTORIA, c.getCodUsuarioApoioVistoria());
		valores.put(COD_USUARIO_TECHNOLOG_VISTORIA, c.getCodUsuarioTechnologVistoria());
		valores.put(DATA_INICIO_VISTORIA, c.getDataInicioVistoria());
		valores.put(DATA_FIM_VISTORIA, c.getDataFimVistoria());
		valores.put(DATA_STATUS_PREENCHIMENTO, c.getDataStatusPreenchimento());
		valores.put(STATUS_PREENCHIMENTO, c.getStatusPreenchimento());
		valores.put(STATUS_CHECK_LIST, c.getStatusCheckList());
		valores.put(COMENTARIOS, c.getComentarios());
		valores.put(NUMERO_CONHECIMENTO_TRANSPORTE, c.getNumeroConhecimentoTransporte());
		valores.put(NUMERO_MANIFESTO, c.getNumeroManifesto());
		valores.put(IDENTIFICADOR_SISTEMAS_TERCEIROS, c.getIdentificadorSistemasTerceiros());
		valores.put(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA, c.getVolumeCombustivelTanquesInicioVistoria());
		valores.put(STATUS_TANQUES_VEICULOS_INICIO_VISTORIA, c.getStatusTanquesVeiculoInicioVistoria());
		valores.put(KM_VEICULO_INICIO_VISTORIA, c.getKmVeiculoInicioVistoria());
		valores.put(LATITUDE_VEICULO_INICIO_VISTORIA, c.getLatitudeVeiculoInicioVistoria());
		valores.put(LONGITUDE_VEICULO_INICIO_VISTORIA, c.getLongitudeVeiculoInicioVistoria());
		valores.put(VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA, c.getVolumeCombustivelTanquesFimVistoria());
		valores.put(STATUS_TANQUES_VEICULO_FIM_VISTORIA, c.getStatusTanquesVeiculoFimVistoria());
		valores.put(KM_VEICULOS_FIM_VISTORIA, c.getKmVeiculoFimVistoria());
		valores.put(LATITUDE_VEICULO_FIM_VISTORIA, c.getLatitudeVeiculoFimVistoria());
		valores.put(LONGITUDE_VEICULO_FIM_VISTORIA, c.getLongitudeVeiculoFimVistoria());
		valores.put(LATITUDE_DISPOSITIVO_CHECK_LIST, c.getLongitudeDispositivoCheckList());
		valores.put(LONGITUDE_DISPOSITIVO_CHECK_LIST, c.getLongitudeDispositivoCheckList());
		valores.put(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR, c.getCaminhoArquivoImagemAssinaturaExecutor());
		valores.put(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO, c.getCaminhoArquivoFotoPlacaVeiculo());
		valores.put(FLG_ATIVO, c.isFlgAtivo());
		valores.put(COD_USUARIO_CLIENTE_RESP, c.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, c.getCodUsuarioClienteResp());
		valores.put(DATA_ACAO, c.getDataAcao());
		valores.put(TIPO_PREENCHIMENTO, c.getTipoPreenchimento());

		// insert row
		return db.insert(T_CHECK_LISTS, null, valores);
	}

	public long insereGrupoItemVistoria(GrupoItemVistoria a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(NOME, a.getNome());
		valores.put(FLG_GRUPO_REBOQUE, a.isFlgGrupoReboque());
		valores.put(FLG_GRUPO_CAVALO, a.isFlgGrupoCavalo());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(DATA_ACAO, a.getDataAcao());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		// insert row

		return db.insert(T_GRUPO_ITEM_VISTORIA, null, valores);
	}

	public long insereImagensEsquema(RelModeloCheckListVeiculos.ImagensEsquema a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(CODIGO_IMAGEM, a.getCodigoImagem());
		valores.put(CAMINHO_ARQUIVO_IMAGEM, a.getCaminhoArquivoImagem());
		valores.put(DESCRICAO_IMAGEM, a.getDescricaoImagem());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		valores.put(DATA_ACAO, a.getDataAcao());

		// insert row

		return db.insert(T_IMAGENS_ESQUEMA_CHECK_LIST, null, valores);
	}

	public long insereItensVistoria(ItensVistoria a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(CODIGO_ITEM, a.getCodigoItem());
		valores.put(NOME_ITEM, a.getNomeItem());
		valores.put(COD_LOCALIZACAO_ITEM, a.getCodLocalizacaoItem());
		valores.put(COD_GRUPO_ITEM, a.getCodGrupoItem());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		valores.put(DATA_ACAO, a.getDataAcao());
		// insert row

		return db.insert(T_ITENS_VISTORIA, null, valores);
	}

	public long insereLocalizacaoItemVistoria(LocalizacaoItemVistoria a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(NOME, a.getNome());
		valores.put(CAMINHO_ARQUIVO_IMAGEM_ILUSTRACAO, a.getCaminhoArquivoImagemIlustracao());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		valores.put(DATA_ACAO, a.getDataAcao());
		// insert row

		return db.insert(T_LOCALIZACAO_ITEM_VISTORIA, null, valores);
	}

	public long insereModelosCheckList(ModelosCheckList a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(NOME, a.getNome());
		valores.put(DESCRICAO, a.getDescricao());
		valores.put(MENSAGENS_GERAIS_EXECUTOR_CHECK_LIST, a.getMensagensGeraisExecutorCheckList());
		valores.put(DATA_ACAO, a.getDataAcao());
		valores.put(FLG_SOMENTE_MOTORISTA_ATUAL_EXECUTA, a.isFlgSomenteMotoristaAtualExecuta());
		valores.put(FLG_SOMENTE_GERENTES_OPERACAO_EXECUTAM, a.isFlgSomenteGerentesOperacaoExecutam());
		valores.put(FLG_SOMENTE_PESSOA_APOIO_EXECUTAM, a.isFlgSomentePessoaApoioExecutam());
		valores.put(FLG_TODAS_PESSOAs_EXECUTAM, a.isFlgTodasPessoasExecutam());
		valores.put(TIPO_DISPONIBILIDADE_MODELO, a.getTipoDisponibilidadeModelo());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		// insert row

		return db.insert(T_MODELOS_CHECK_LIST, null, valores);
	}

	public long insereRelModeloCheckListImagemEsquema(RelModeloCheckListImagemEsquema a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(COD_MODELO_CHECK_LIST, a.getCodModeloCheckList());
		valores.put(COD_IMAGEM_ESQUEMA_CHECK_LIST, a.getCodImagemEsquemaCheckList());
		valores.put(CODIGO_IMAGEM, a.getCodigoImagem());
		valores.put(DESCRICAO_IMAGEM, a.getDescricaoImagem());
		valores.put(FLG_ATIVO, a.getFlgAtivo());
		valores.put(DATA_ACAO, a.getDataAcao());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		// insert row

		return db.insert(T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA, null, valores);
	}

	public long insereRelModeloCheckListItensVistoria(RelModeloCheckListItensVistoria a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();
		valores.put(COD, a.getCod());
		valores.put(COD_MODELO_CHECK_LIST, a.getCodModeloCheckList());
		valores.put(COD_ITEM_VISTORIA, a.getCodItemVistoria());
		valores.put(CODIGO_ITEM, a.getCodigoItem());
		valores.put(COD_GRUPO_ITEM_VISTORIA, a.getCodigoGrupoItemVistoria());
		//System.out.println(COD_LOCALIZACAO_ITEM_VISTORIA +" " + a.getCodigoLocalizacaoItemVistoria());
		//System.out.println(COD_LOCALIZACAO_ITEM_VISTORIA);
		valores.put(COD_LOCALIZACAO_ITEM_VISTORIA, a.getCodigoLocalizacaoItemVistoria());
		valores.put(FLG_OPCAO_OK, a.isFlgOpcaoOK());
		valores.put(FLG_OPCAO_NAO_OK, a.isFlgOpcaoNaoOk());
		valores.put(FLG_OPCAO_NAO_EFETUADO, a.isFlgOpcaoNaoEfetuado());
		valores.put(FLG_OPCAO_OBSERVACAO, a.isFlgOpcaoObservacao());
		valores.put(FLG_OPCAO_ANEXO, a.isFlgOpcaoAnexo());
		valores.put(ORDEM_SEQUENCIA_VISTORIA, a.getOrdemSequenciaVistoria());
		valores.put(MENSAGEM_EXECUTOR_VISTORIA_ITEM, a.getMensagemExecutorVistoriaItem());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_ACAO, a.getDataAcao());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());

		// insert row

		return db.insert(T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA, null, valores);
	}

	public long insereRelModeloCheckListVeiculos(RelModeloCheckListVeiculos a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(COD_VEICULO, a.getCodVeiculos());
		valores.put(COD_VEICULO_W, a.getCodVeiculoW());
		valores.put(PLACA_VEICULO, a.getPlacaVeiculo());
		valores.put(COD_MODELO_CHECK_LIST, a.getCodModeloCheckList());
		valores.put(FLG_ATIVO, a.isFlgAtivo());
		valores.put(DATA_ACAO, a.getDataAcao());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp().toString());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		// insert row

		return db.insert(T_REL_MODELO_CHECK_LIST_VEICULOS, null, valores);
	}

	public long insereVeiculos(Veiculos a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(PLACA, a.getPlaca());
		valores.put(COD_VEICULO_W, a.getCodW());

		// insert row

		return db.insert(T_VEICULOS, null, valores);
	}

	public long insereRelPermissaoPessoasApoioCheckListVeiculos(RelPermissaoPessoasApoioCheckListVeiculos a, long[] tag_ids) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD, a.getCod());
		valores.put(COD_PESSOA_APOIO, a.getCodPessoaApoio());
		valores.put(COD_USUARIO_PESSOA_APOIO, a.getCodUsuarioPessoaApoio());
		valores.put(COD_VEICULO_W, a.getCodVeiculoW());
		valores.put(COD_VEICULO, a.getCodVeiculo());
		valores.put(FLG_ATIVO, a.getFlgAtivo());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		valores.put(DATA_ACAO, a.getDataAcao());

		// insert row

		return db.insert(T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS, null, valores);
	}

	public long insereUsuarioPessoasApoio(UsuarioPessoasApoio a, long[] tag_ids) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();
		System.out.println("antes ok ");
		// insert row
		valores.put(COD, a.getCod());
		valores.put(COD_PESSOA_APOIO, a.getCodPessoaApoio());
		valores.put(USUARIO, a.getUsuario());
		valores.put(SENHA, a.getSenha());
		valores.put(FLG_ATIVO, a.getFlgAtivo());
		valores.put(DATA_CADASTRO, a.getDataCadastro());
		valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());
		valores.put(DATA_ACAO, a.getDataAcao());
		return db.insert(T_USUARIOS_PESSOAS_APOIO, null, valores);
	}

	public long insereUsuarios(Usuarios a) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();
		System.out.println("antes ok ");
		// insert row
		valores.put(COD_DARWIN, a.getCod());
		valores.put(LOGIN, a.getLogin());
		valores.put(SENHA, a.getSenha());
		valores.put(COD_CLIENTE, a.getCod_cliente());
		valores.put(RAZAO_SOCIAL, a.getRazao_social());
		valores.put(CAMINHO_LOGO, a.getCaminho_logo());
		valores.put(TIPO_USUARIO, a.getTipoUsuario());
		return db.insert(T_USUARIOS, null, valores);
	}

	public ArrayList<Veiculos> getVeiculos() {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + T_VEICULOS +
				" WHERE " + COD_VEICULO_W + " is NOT NULL " + " ORDER BY " + PLACA;
		Log.e(LOG, selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<Veiculos> list = new ArrayList<>();
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				Veiculos veiculo = new Veiculos();

				veiculo.setCod(c.getInt(c.getColumnIndex(COD)));
				veiculo.setPlaca(c.getString(c.getColumnIndex(PLACA)));
				veiculo.setCodW(c.getInt(c.getColumnIndex(COD_VEICULO_W)));
				list.add(veiculo);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<ModelosCheckList> getModelos(String placa, int tipo) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery;
		if (tipo == 2) {
			selectQuery = "SELECT  * FROM " + T_REL_MODELO_CHECK_LIST_VEICULOS +
					" AS t_vec INNER JOIN " + T_MODELOS_CHECK_LIST +
					" AS t_modelos ON t_vec.cod_modelo_check_list = t_modelos.cod " +
					" AND t_vec.placa_veiculo = '" + placa + "'" +
					" AND (t_modelos.flg_todas_pessoas_executam = 1 OR t_modelos." + FLG_SOMENTE_MOTORISTA_ATUAL_EXECUTA + " = 1)";
		} else {
			selectQuery = "SELECT  * FROM " + T_REL_MODELO_CHECK_LIST_VEICULOS +
					" AS t_vec INNER JOIN " + T_MODELOS_CHECK_LIST +
					" AS t_modelos ON t_vec.cod_modelo_check_list = t_modelos.cod " +
					" AND t_vec.placa_veiculo = '" + placa + "'" +
					" AND t_modelos.flg_todas_pessoas_executam = 1";
		}

		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<ModelosCheckList> list = new ArrayList<>();
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				ModelosCheckList modelos = new ModelosCheckList();
				modelos.setCod(c.getInt(c.getColumnIndex(COD)));
				modelos.setDescricao(c.getString(c.getColumnIndex(DESCRICAO)));
				modelos.setNome(c.getString(c.getColumnIndex(NOME)));
				modelos.setMensagensGeraisExecutorCheckList(c.getString(c.getColumnIndex(MENSAGENS_GERAIS_EXECUTOR_CHECK_LIST)));
				//System.out.println(Arrays.toString(c.getColumnNames()));
				list.add(modelos);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<GrupoItemVistoria> getGrupoItem(ArrayList<Integer> array) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<GrupoItemVistoria> list = new ArrayList<>();

		for (int i = 0; i < array.size(); i++) {
			String selectQuery = "SELECT  * FROM " + T_GRUPO_ITEM_VISTORIA +
					" WHERE " + COD + " = " + array.get(i);
			Cursor c = db.rawQuery(selectQuery, null);
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					GrupoItemVistoria grupos = new GrupoItemVistoria();
					grupos.setCod(c.getInt(c.getColumnIndex(COD)));
					grupos.setNome(c.getString(c.getColumnIndex(NOME)));
					grupos.setFlgAtivo(c.getInt(c.getColumnIndex(FLG_ATIVO)) > 0);
					grupos.setFlgGrupoCavalo(c.getInt(c.getColumnIndex(FLG_GRUPO_CAVALO)) > 0);
					grupos.setFlgGrupoReboque(c.getInt(c.getColumnIndex(FLG_GRUPO_REBOQUE)) > 0);
					//System.out.println(Arrays.toString(c.getColumnNames()));
					list.add(grupos);
					c.moveToNext();
				}
			}
			c.close();
		}
		db.close();
		return list;
	}

	public ArrayList<ItensCheckList> getItens(Integer cod_check) {
		SQLiteDatabase db = this.getReadableDatabase();
		System.out.println(cod_check);
		String selectQuery = "SELECT * FROM " + T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA +
				" AS rel INNER JOIN " + T_ITENS_VISTORIA +
				" AS itens ON rel.cod_item_vistoria = itens.cod" +
				" AND rel.cod_modelo_check_list = " + cod_check +
				" AND rel.flg_ativo=1";

		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<ItensCheckList> list = new ArrayList<>();
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				ItensCheckList itens = new ItensCheckList();
				itens.setCodItemVistoria(c.getInt(c.getColumnIndex(COD_ITEM_VISTORIA)));
				itens.setFlgOpcaoOK(c.getInt(c.getColumnIndex(FLG_OPCAO_OK)) > 0);
				itens.setFlgOpcaoNaoOK(c.getInt(c.getColumnIndex(FLG_OPCAO_NAO_OK)) > 0);
				itens.setFlgOpcaoNaoEfet(c.getInt(c.getColumnIndex(FLG_OPCAO_NAO_EFETUADO)) > 0);
				itens.setFlgOpcaoAnexo(c.getInt(c.getColumnIndex(FLG_OPCAO_ANEXO)) > 0);
				itens.setFlgOpcaoObs(c.getInt(c.getColumnIndex(FLG_OPCAO_OBSERVACAO)) > 0);
				itens.setOrdemSequenciaVistoria(c.getInt(c.getColumnIndex(ORDEM_SEQUENCIA_VISTORIA)));
				itens.setNomeItem(c.getString(c.getColumnIndex(NOME_ITEM)));
				itens.setCodGrupoItem(c.getInt(c.getColumnIndex(COD_GRUPO_ITEM)));
				itens.setCodLocalizacaoItem(c.getInt(c.getColumnIndex(COD_LOCALIZACAO_ITEM)));
				itens.setMensagensGerais(c.getString(c.getColumnIndex(MENSAGEM_EXECUTOR_VISTORIA_ITEM)));
				list.add(itens);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public long saveCheckList(ArrayList<ArrayList<ItensCheckList>> dados, CheckLists check) {
		SQLiteDatabase db = this.getReadableDatabase();
		Log.e("DEBUGG", "saveCheckList: " + check.getFlgSomenteReboques());
		Long checkId = insereCheckLists(check);
		int tamanho = dados.size();
		for (int i = 0; i < tamanho; i++) {
			int tamanhoj = dados.get(i).size();
			for (int j = 0; j < tamanhoj; j++) {
				insereAnaliseItensCheckList(dados.get(i).get(j), checkId.intValue());
			}
		}
		db.close();

		return checkId;
	}

	public long insereAnaliseItensCheckList(ItensCheckList a, Integer codigoCheck) {

		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD_CHECK_LIST, codigoCheck);
		valores.put(COD_ITEM_VISTORIA, a.getCodItemVistoria());
		valores.put(FLG_OK, a.getOpcaoOK());
		valores.put(FLG_NAO_OK, a.getOpcaoNaoOK());
		valores.put(FLG_NAO_EFETUADO, a.getOpcaoNaoEfet());
		valores.put(FLG_NECESSITA_TROCA_REPARO, false);
		valores.put(OBSERVACAO_EXECUTOR, a.getOpcaoObs());
		valores.put(ARQUIVO_ANEXO_CHECK_LIST, a.getOpcaoAnexo());
		valores.put(FLG_ATIVO, true);
		valores.put(DATA_ACAO, a.getDataAcao());
		//valores.put(COD_USUARIO_CLIENTE_RESP, a.getCodUsuarioClienteResp());
		//valores.put(COD_USUARIO_RESP, a.getCodUsuarioResp());

		// insert row

		return db.insert(T_ANALISE_ITENS_CHECK_LIST, null, valores);
	}

	public long updateCheckList(ArrayList<ArrayList<ItensCheckList>> dados, CheckLists check) {
		SQLiteDatabase db = this.getReadableDatabase();

		// = insereCheckLists(check,null);

		Log.e("DEBUGG", "updateCheckList: " + check.getStatusPreenchimento());
		Long checkId = updateRowCheckLists(check);
		//TODO: REMOVER LINHAS DA TABELA ANALISE ITENS QUE  TIVEREM esse checkID
		String sql = "DELETE FROM t_analise_itens_check_list WHERE cod_check_list = " + checkId.toString();
		System.out.println(sql);
		query(sql);

		int tamanho = dados.size();
		for (int i = 0; i < tamanho; i++) {
			int tamanhoj = dados.get(i).size();
			for (int j = 0; j < tamanhoj; j++) {
				insereAnaliseItensCheckList(dados.get(i).get(j), checkId.intValue());
			}
		}
		db.close();

		return checkId;
	}

	public void query(String sql) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL(sql);
		db.close();
	}

	public long updateRowCheckLists(CheckLists c) {
		Log.e("fail", "entrou no update");
		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(COD_VEICULO, c.getCodVeiculo());
		valores.put(COD_MODELO_CHECK_LIST, c.getCodModeloCheckList());
		valores.put(COD_VEICULO_W, c.getCodVeiculoW());
		valores.put(PLACA_VEICULO, c.getPlacaVeiculo());
		valores.put(TIPO_SELECAO_VEICULO, c.getTipoSelecaoVeiculo());
		valores.put(PLACAS_CARRETAS, c.getPlacasCarretas());
		valores.put(FLG_SOMENTE_REBOQUES, c.isFlgSomenteReboques());
		valores.put(RFID_CARRETAS, c.getRfidCarretas());
		valores.put(IDENTIFICADOR_DIGITAL_ULTIMO_MOTORISTA_VEICULO, c.getIdentificadorDigitalUltimoMotoristaVeiculo());
		valores.put(IDENTIFICADOR_DIGITAL_MOTORISTA_ATUAL, c.getIdentificadorDigitalMotoristaAtual());
		valores.put(IDENTIFICADOR_DIGITAL_PESSOA_APOIO_ATUAL, c.getIdentificadorDigitalPessoaApoioAtual());
		valores.put(IDENTIFICADOR_DIGITAL_MOTORISTA_VISTORIA, c.getIdentificadorDigitalMotoristaVistoria());
		valores.put(COD_USUARIO_CLIENTE_VISTORIA, c.getCodUsuarioClienteVistoria());
		valores.put(COD_PESSOA_APOIO_VISTORIA, c.getCodUsuarioApoioVistoria());
		valores.put(COD_USUARIO_TECHNOLOG_VISTORIA, c.getCodUsuarioTechnologVistoria());
		valores.put(DATA_INICIO_VISTORIA, c.getDataInicioVistoria());
		valores.put(DATA_FIM_VISTORIA, c.getDataFimVistoria());
		valores.put(DATA_STATUS_PREENCHIMENTO, c.getDataStatusPreenchimento());
		valores.put(STATUS_PREENCHIMENTO, c.getStatusPreenchimento());
		valores.put(STATUS_CHECK_LIST, c.getStatusCheckList());
		valores.put(COMENTARIOS, c.getComentarios());
		valores.put(NUMERO_CONHECIMENTO_TRANSPORTE, c.getNumeroConhecimentoTransporte());
		valores.put(NUMERO_MANIFESTO, c.getNumeroManifesto());
		valores.put(IDENTIFICADOR_SISTEMAS_TERCEIROS, c.getIdentificadorSistemasTerceiros());
		valores.put(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA, c.getVolumeCombustivelTanquesInicioVistoria());
		valores.put(STATUS_TANQUES_VEICULOS_INICIO_VISTORIA, c.getStatusTanquesVeiculoInicioVistoria());
		valores.put(KM_VEICULO_INICIO_VISTORIA, c.getKmVeiculoInicioVistoria());
		valores.put(LATITUDE_VEICULO_INICIO_VISTORIA, c.getLatitudeVeiculoInicioVistoria());
		valores.put(LONGITUDE_VEICULO_INICIO_VISTORIA, c.getLongitudeVeiculoInicioVistoria());
		valores.put(VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA, c.getVolumeCombustivelTanquesFimVistoria());
		valores.put(STATUS_TANQUES_VEICULO_FIM_VISTORIA, c.getStatusTanquesVeiculoFimVistoria());
		valores.put(KM_VEICULOS_FIM_VISTORIA, c.getKmVeiculoFimVistoria());
		valores.put(LATITUDE_VEICULO_FIM_VISTORIA, c.getLatitudeVeiculoFimVistoria());
		valores.put(LONGITUDE_VEICULO_FIM_VISTORIA, c.getLongitudeVeiculoFimVistoria());
		valores.put(LATITUDE_DISPOSITIVO_CHECK_LIST, c.getLongitudeDispositivoCheckList());
		valores.put(LONGITUDE_DISPOSITIVO_CHECK_LIST, c.getLongitudeDispositivoCheckList());
		valores.put(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR, c.getCaminhoArquivoImagemAssinaturaExecutor());
		valores.put(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO, c.getCaminhoArquivoFotoPlacaVeiculo());
		valores.put(FLG_ATIVO, c.isFlgAtivo());
		valores.put(COD_USUARIO_CLIENTE_RESP, c.getCodUsuarioClienteResp());
		valores.put(COD_USUARIO_RESP, c.getCodUsuarioClienteResp());
		valores.put(DATA_ACAO, c.getDataAcao());
		valores.put(TIPO_PREENCHIMENTO, c.getTipoPreenchimento());

		// insert row
		String where = COD + " =  ?";
		String[] clause = new String[1];
		clause[0] = c.getCod().toString();

		long respCod = db.update(T_CHECK_LISTS, valores, where, clause);
		db.close();
		System.out.println(respCod);
		return c.getCod();
	}
	
	public void clearTables() {
		SQLiteDatabase db = this.getReadableDatabase();

		db.delete(T_LOCALIZACAO_ITEM_VISTORIA, null, null);
		db.delete(T_GRUPO_ITEM_VISTORIA, null, null);
		db.delete(T_ITENS_VISTORIA, null, null);
		db.delete(T_USUARIOS_PESSOAS_APOIO, null, null);
		db.delete(T_IMAGENS_ESQUEMA_CHECK_LIST, null, null);
		db.delete(T_MODELOS_CHECK_LIST, null, null);
		db.delete(T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA, null, null);
		db.delete(T_CHECK_LISTS, null, null);
		db.delete(T_ANALISE_ITENS_CHECK_LIST, null, null);
		db.delete(T_REL_PERMISSAO_PESSOAS_APOIO_CHECK_LIST_VEICULOS, null, null);
		db.delete(T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA, null, null);
		db.delete(T_REL_MODELO_CHECK_LIST_VEICULOS, null, null);
		db.delete(T_VEICULOS, null, null);
		db.delete(T_USUARIOS, null, null);
	}

	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}


	public ArrayList<CheckLists> getCheckSavedList() {

		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT *, checklist.cod as cod_check FROM " + T_CHECK_LISTS
				+ " as checklist, " + T_MODELOS_CHECK_LIST + " as model "
				+ "WHERE checklist." + STATUS_PREENCHIMENTO
				+ " = 1 AND model." + COD + "= checklist." + COD_MODELO_CHECK_LIST
				+ " AND model." + FLG_ATIVO + " = 1 AND checklist." + FLG_ATIVO + "= 1";

		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<CheckLists> list = new ArrayList<>();
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {
			System.out.println(selectQuery);
			while (!c.isAfterLast()) {
				//TODO: Terminar o modelo

				CheckLists check = new CheckLists();
				check.setCod(c.getInt(c.getColumnIndex("cod_check")));
				check.setCodVeiculo(c.getInt(c.getColumnIndex(COD_VEICULO)));
				check.setCodModeloCheckList(c.getInt(c.getColumnIndex(COD_MODELO_CHECK_LIST)));
				check.setCodVeiculoW(c.getInt(c.getColumnIndex(COD_VEICULO_W)));
				check.setDataStatusPreenchimento(c.getString(c.getColumnIndex(DATA_STATUS_PREENCHIMENTO)));
				check.setPlacaVeiculo(c.getString(c.getColumnIndex(PLACA_VEICULO)));
				check.setNomecheck(c.getString(c.getColumnIndex(NOME)));

				check.setDataInicioVistoria(c.getString(c.getColumnIndex(DATA_INICIO_VISTORIA)));
				check.setComentarios(c.getString(c.getColumnIndex(COMENTARIOS)));


				check.setStatusTanquesVeiculoInicioVistoria(c.getDouble(c.getColumnIndex(STATUS_TANQUES_VEICULOS_INICIO_VISTORIA)));
				check.setVolumeCombustivelTanquesInicioVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA)));

				check.setCaminhoArquivoFotoPlacaVeiculo(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO)));
				check.setCaminhoArquivoImagemAssinaturaExecutor(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR)));

				check.setFlgSomenteReboques(c.getInt(c.getColumnIndex(FLG_SOMENTE_REBOQUES)) > 0);

				Log.e("ERROR", "getCheckSavedList: " + c.getInt(c.getColumnIndex(FLG_SOMENTE_REBOQUES)));
				check.setNumeroConhecimentoTransporte(c.getString(c.getColumnIndex(NUMERO_CONHECIMENTO_TRANSPORTE)));
				check.setStatusCheckList(c.getInt(c.getColumnIndex(STATUS_CHECK_LIST)));

				check.setNumeroManifesto(c.getString(c.getColumnIndex(NUMERO_MANIFESTO)));
				check.setIdentificadorSistemasTerceiros(c.getString(c.getColumnIndex(IDENTIFICADOR_SISTEMAS_TERCEIROS)));


				list.add(check);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<CheckLists> getCheckEndList() {

		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT *, checklist.cod as cod_check FROM " + T_CHECK_LISTS
				+ " as checklist, " + T_MODELOS_CHECK_LIST + " as model "
				+ "WHERE model." + COD + "= checklist." + COD_MODELO_CHECK_LIST + " AND "
				+ STATUS_PREENCHIMENTO + " = 2";

		//String selectQuery = "SELECT * FROM "+ T_CHECK_LISTS + " WHERE "+ STATUS_PREENCHIMENTO + " = 2" ;
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<CheckLists> list = new ArrayList<>();
		System.out.println(selectQuery);
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {

			while (!c.isAfterLast()) {
				//TODO: Terminar o modelo

				CheckLists check = new CheckLists();
				check.setCod(c.getInt(c.getColumnIndex("cod_check")));
				check.setCodModeloCheckList(c.getInt(c.getColumnIndex(COD_MODELO_CHECK_LIST)));
				check.setCodVeiculoW(c.getInt(c.getColumnIndex(COD_VEICULO_W)));
				check.setDataStatusPreenchimento(c.getString(c.getColumnIndex(DATA_STATUS_PREENCHIMENTO)));
				check.setPlacaVeiculo(c.getString(c.getColumnIndex(PLACA_VEICULO)));
				check.setNomecheck(c.getString(c.getColumnIndex(NOME)));

				check.setDataInicioVistoria(c.getString(c.getColumnIndex(DATA_INICIO_VISTORIA)));
				check.setComentarios(c.getString(c.getColumnIndex(COMENTARIOS)));


				check.setStatusTanquesVeiculoInicioVistoria(c.getDouble(c.getColumnIndex(STATUS_TANQUES_VEICULOS_INICIO_VISTORIA)));
				check.setVolumeCombustivelTanquesInicioVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA)));

				check.setCaminhoArquivoFotoPlacaVeiculo(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO)));
				check.setCaminhoArquivoImagemAssinaturaExecutor(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR)));

				check.setFlgSomenteReboques(c.getInt(c.getColumnIndex(FLG_SOMENTE_REBOQUES)) > 0);

				check.setNumeroConhecimentoTransporte(c.getString(c.getColumnIndex(NUMERO_CONHECIMENTO_TRANSPORTE)));
				check.setStatusCheckList(c.getInt(c.getColumnIndex(STATUS_CHECK_LIST)));

				check.setNumeroManifesto(c.getString(c.getColumnIndex(NUMERO_MANIFESTO)));
				check.setIdentificadorSistemasTerceiros(c.getString(c.getColumnIndex(IDENTIFICADOR_SISTEMAS_TERCEIROS)));


				list.add(check);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<CheckLists> getCheckSendList() {

		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT *, checklist.cod as cod_check FROM " + T_CHECK_LISTS
				+ " as checklist, " + T_MODELOS_CHECK_LIST + " as model "
				+ "WHERE model." + COD + "= checklist." + COD_MODELO_CHECK_LIST + " AND "
				+ STATUS_PREENCHIMENTO + " != 1";

		//String selectQuery = "SELECT * FROM "+ T_CHECK_LISTS + " WHERE "+ STATUS_PREENCHIMENTO + " = 2" ;
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<CheckLists> list = new ArrayList<>();
		System.out.println(selectQuery);
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {

			while (!c.isAfterLast()) {
				//TODO: Terminar o modelo

				CheckLists check = new CheckLists();
				check.setCod(c.getInt(c.getColumnIndex("cod_check")));
				check.setCodModeloCheckList(c.getInt(c.getColumnIndex(COD_MODELO_CHECK_LIST)));
				check.setCodVeiculoW(c.getInt(c.getColumnIndex(COD_VEICULO_W)));
				check.setDataStatusPreenchimento(c.getString(c.getColumnIndex(DATA_STATUS_PREENCHIMENTO)));
				check.setPlacaVeiculo(c.getString(c.getColumnIndex(PLACA_VEICULO)));
				check.setNomecheck(c.getString(c.getColumnIndex(NOME)));

				check.setDataInicioVistoria(c.getString(c.getColumnIndex(DATA_INICIO_VISTORIA)));
				check.setComentarios(c.getString(c.getColumnIndex(COMENTARIOS)));


				check.setStatusTanquesVeiculoInicioVistoria(c.getDouble(c.getColumnIndex(STATUS_TANQUES_VEICULOS_INICIO_VISTORIA)));
				check.setVolumeCombustivelTanquesInicioVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA)));

				check.setCaminhoArquivoFotoPlacaVeiculo(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO)));
				Log.e("vamos ver auqi", "getCheckSendList: " + check.getCaminhoArquivoFotoPlacaVeiculo());
				check.setCaminhoArquivoImagemAssinaturaExecutor(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR)));

				check.setFlgSomenteReboques(c.getInt(c.getColumnIndex(FLG_SOMENTE_REBOQUES)) > 0);

				check.setNumeroConhecimentoTransporte(c.getString(c.getColumnIndex(NUMERO_CONHECIMENTO_TRANSPORTE)));
				check.setStatusCheckList(c.getInt(c.getColumnIndex(STATUS_CHECK_LIST)));

				check.setNumeroManifesto(c.getString(c.getColumnIndex(NUMERO_MANIFESTO)));
				check.setIdentificadorSistemasTerceiros(c.getString(c.getColumnIndex(IDENTIFICADOR_SISTEMAS_TERCEIROS)));


				list.add(check);
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<ItensCheckList> getSavedItens(Integer cod_check, Integer cod_modelo) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM "
				+ T_ITENS_VISTORIA + " as itens INNER JOIN "
				+ T_ANALISE_ITENS_CHECK_LIST + " as analise ON itens."
				+ COD + " = analise." + COD_ITEM_VISTORIA
				+ " AND itens." + FLG_ATIVO + " = 1 AND analise." + COD_CHECK_LIST
				+ " = " + cod_check + " AND analise." + FLG_ATIVO + " = 1 INNER JOIN "
				+ T_REL_MODELO_CHECK_LIST_ITENS_VISTORIA + " as rel "
				+ "ON itens." + COD + " = rel." + COD_ITEM_VISTORIA + " AND rel."
				+ FLG_ATIVO + " = 1 AND rel." + COD_MODELO_CHECK_LIST + " = " + cod_modelo + ";";


		Log.e("query", selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		ArrayList<ItensCheckList> list = new ArrayList<>();
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				ItensCheckList itens = new ItensCheckList();
				itens.setCodItemVistoria(c.getInt(c.getColumnIndex(COD_ITEM_VISTORIA)));
				itens.setFlgOpcaoOK(c.getInt(c.getColumnIndex(FLG_OPCAO_OK)) > 0);
				itens.setFlgOpcaoNaoOK(c.getInt(c.getColumnIndex(FLG_OPCAO_NAO_OK)) > 0);
				itens.setFlgOpcaoNaoEfet(c.getInt(c.getColumnIndex(FLG_OPCAO_NAO_EFETUADO)) > 0);
				itens.setFlgOpcaoAnexo(c.getInt(c.getColumnIndex(FLG_OPCAO_ANEXO)) > 0);
				itens.setFlgOpcaoObs(c.getInt(c.getColumnIndex(FLG_OPCAO_OBSERVACAO)) > 0);
				itens.setOrdemSequenciaVistoria(c.getInt(c.getColumnIndex(ORDEM_SEQUENCIA_VISTORIA)));
				itens.setNomeItem(c.getString(c.getColumnIndex(NOME_ITEM)));
				itens.setCodGrupoItem(c.getInt(c.getColumnIndex(COD_GRUPO_ITEM)));
				itens.setCodLocalizacaoItem(c.getInt(c.getColumnIndex(COD_LOCALIZACAO_ITEM)));
				itens.setMensagensGerais(c.getString(c.getColumnIndex(MENSAGEM_EXECUTOR_VISTORIA_ITEM)));

				itens.setOpcaoOK(c.getInt(c.getColumnIndex(FLG_OK)) > 0);
				itens.setOpcaoNaoOK(c.getInt(c.getColumnIndex(FLG_NAO_OK)) > 0);

				itens.setOpcaoNaoEfet(c.getInt(c.getColumnIndex(FLG_NAO_EFETUADO)) > 0);
				itens.setOpcaoAnexo(c.getString(c.getColumnIndex(ARQUIVO_ANEXO_CHECK_LIST)));
				itens.setOpcaoObs(c.getString(c.getColumnIndex(OBSERVACAO_EXECUTOR)));
				list.add(itens);
				System.out.println(itens.toString());
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public CheckLists getCheckByCod(Integer cod) {
		String selectQuery = "SELECT * FROM " +
				T_CHECK_LISTS + " WHERE cod = " +
				cod + " LIMIT 1;";
		SQLiteDatabase db = this.getReadableDatabase();


		Log.e("query", selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		CheckLists check = new CheckLists();
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				check.setCod(c.getInt(c.getColumnIndex(COD)));
				check.setCodVeiculo(c.getInt(c.getColumnIndex(COD_VEICULO)));
				check.setCodModeloCheckList(c.getInt(c.getColumnIndex(COD_MODELO_CHECK_LIST)));
				check.setCodVeiculoW(c.getInt(c.getColumnIndex(COD_VEICULO_W)));
				check.setPlacaVeiculo(c.getString(c.getColumnIndex(PLACA_VEICULO)));
				check.setTipoSelecaoVeiculo(c.getInt(c.getColumnIndex(TIPO_SELECAO_VEICULO)));
				check.setPlacasCarretas(c.getString(c.getColumnIndex(PLACAS_CARRETAS)));
				check.setFlgSomenteReboques(c.getInt(c.getColumnIndex(FLG_SOMENTE_REBOQUES)) > 0);
				check.setRfidCarretas(c.getString(c.getColumnIndex(RFID_CARRETAS)));
				check.setIdentificadorDigitalUltimoMotoristaVeiculo(c.getString(c.getColumnIndex(IDENTIFICADOR_DIGITAL_ULTIMO_MOTORISTA_VEICULO)));
				check.setIdentificadorDigitalMotoristaAtual(c.getString(c.getColumnIndex(IDENTIFICADOR_DIGITAL_MOTORISTA_ATUAL)));
				check.setIdentificadorDigitalPessoaApoioAtual(c.getString(c.getColumnIndex(IDENTIFICADOR_DIGITAL_PESSOA_APOIO_ATUAL)));
				check.setIdentificadorDigitalMotoristaVistoria(c.getString(c.getColumnIndex(IDENTIFICADOR_DIGITAL_MOTORISTA_VISTORIA)));
				check.setCodUsuarioClienteVistoria(c.getInt(c.getColumnIndex(COD_USUARIO_CLIENTE_VISTORIA)));
				check.setCodUsuarioApoioVistoria(c.getInt(c.getColumnIndex(COD_PESSOA_APOIO_VISTORIA)));
				check.setCodUsuarioTechnologVistoria(c.getInt(c.getColumnIndex(COD_USUARIO_TECHNOLOG_VISTORIA)));
				check.setDataInicioVistoria(c.getString(c.getColumnIndex(DATA_INICIO_VISTORIA)));
				check.setDataFimVistoria(c.getString(c.getColumnIndex(DATA_FIM_VISTORIA)));
				check.setStatusPreenchimento(c.getInt(c.getColumnIndex(STATUS_PREENCHIMENTO)));
				check.setDataStatusPreenchimento(c.getString(c.getColumnIndex(DATA_STATUS_PREENCHIMENTO)));
				check.setStatusCheckList(c.getInt(c.getColumnIndex(STATUS_CHECK_LIST)));
				check.setComentarios(c.getString(c.getColumnIndex(COMENTARIOS)));
				check.setNumeroConhecimentoTransporte(c.getString(c.getColumnIndex(NUMERO_CONHECIMENTO_TRANSPORTE)));
				check.setNumeroManifesto(c.getString(c.getColumnIndex(NUMERO_MANIFESTO)));
				check.setIdentificadorSistemasTerceiros(c.getString(c.getColumnIndex(IDENTIFICADOR_SISTEMAS_TERCEIROS)));
				check.setVolumeCombustivelTanquesInicioVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_INICIO_VISTORIA)));
				check.setVolumeCombustivelTanquesFimVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA)));
				check.setKmVeiculoInicioVistoria(c.getDouble(c.getColumnIndex(KM_VEICULO_INICIO_VISTORIA)));
				check.setLatitudeVeiculoInicioVistoria(c.getString(c.getColumnIndex(LATITUDE_VEICULO_INICIO_VISTORIA)));
				check.setLongitudeVeiculoInicioVistoria(c.getString(c.getColumnIndex(LONGITUDE_VEICULO_INICIO_VISTORIA)));
				check.setVolumeCombustivelTanquesFimVistoria(c.getDouble(c.getColumnIndex(VOLUME_COMBUSTIVEL_TANQUES_FIM_VISTORIA)));
				check.setStatusTanquesVeiculoFimVistoria(c.getDouble(c.getColumnIndex(STATUS_TANQUES_VEICULO_FIM_VISTORIA)));
				check.setKmVeiculoFimVistoria(c.getDouble(c.getColumnIndex(KM_VEICULOS_FIM_VISTORIA)));
				check.setLatitudeVeiculoFimVistoria(c.getString(c.getColumnIndex(LATITUDE_VEICULO_FIM_VISTORIA)));
				check.setLongitudeVeiculoFimVistoria(c.getString(c.getColumnIndex(LONGITUDE_VEICULO_FIM_VISTORIA)));
				check.setLatitudeDispositivoCheckList(c.getString(c.getColumnIndex(LATITUDE_DISPOSITIVO_CHECK_LIST)));
				check.setLongitudeDispositivoCheckList(c.getString(c.getColumnIndex(LONGITUDE_DISPOSITIVO_CHECK_LIST)));
				check.setCaminhoArquivoImagemAssinaturaExecutor(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM_ASSINATURA_EXECUTOR)));
				check.setFlgAtivo(c.getInt(c.getColumnIndex(FLG_ATIVO)) > 0);
				check.setCodUsuarioClienteResp(c.getInt(c.getColumnIndex(COD_USUARIO_CLIENTE_RESP)));
				check.setCodUsuarioResp(c.getInt(c.getColumnIndex(COD_USUARIO_RESP)));
				check.setDataAcao(c.getString(c.getColumnIndex(DATA_ACAO)));
				check.setTipoPreenchimento(c.getInt(c.getColumnIndex(TIPO_PREENCHIMENTO)));
				check.setCaminhoArquivoFotoPlacaVeiculo(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_FOTO_PLACA_VEICULO)));

				c.moveToNext();
			}
		}
		c.close();
		db.close();

		return check;
	}

	public ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> getListImages(Integer CodigoModelo) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM "
				+ T_IMAGENS_ESQUEMA_CHECK_LIST + " as i INNER JOIN "
				+ T_REL_MODELO_CHECK_LIST_IMAGEM_ESQUEMA + " as rel "
				+ " ON i." + COD + " = rel." + COD_IMAGEM_ESQUEMA_CHECK_LIST
				+ " AND rel." + COD_MODELO_CHECK_LIST + " = " + CodigoModelo;


		Log.e("query", selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> list = new ArrayList<>();
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				RelModeloCheckListVeiculos.ImagensEsquema imagens = new RelModeloCheckListVeiculos.ImagensEsquema();
				imagens.setCod(c.getInt(c.getColumnIndex(COD)));
				imagens.setCodigoImagem(c.getString(c.getColumnIndex(CODIGO_IMAGEM)));
				imagens.setCaminhoArquivoImagem(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM)));
				imagens.setDescricaoImagem(c.getString(c.getColumnIndex(DESCRICAO_IMAGEM)));
				list.add(imagens);
				System.out.println(imagens.toString());
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> getAllListImages() {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM "
				+ T_IMAGENS_ESQUEMA_CHECK_LIST;


		Log.e("query", selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);

		ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> list = new ArrayList<>();
		Log.e("BANCO DE DADOS DAORA", "Qts linhas ta vindo? " + c.getCount());
		//System.out.println(Arrays.toString(c.getColumnNames()));
		System.out.println(Arrays.toString(c.getColumnNames()));
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				RelModeloCheckListVeiculos.ImagensEsquema imagens = new RelModeloCheckListVeiculos.ImagensEsquema();
				imagens.setCod(c.getInt(c.getColumnIndex(COD)));
				imagens.setCodigoImagem(c.getString(c.getColumnIndex(CODIGO_IMAGEM)));
				imagens.setCaminhoArquivoImagem(c.getString(c.getColumnIndex(CAMINHO_ARQUIVO_IMAGEM)));
				imagens.setDescricaoImagem(c.getString(c.getColumnIndex(DESCRICAO_IMAGEM)));
				list.add(imagens);
				System.out.println(imagens.toString());
				c.moveToNext();
			}
		}
		c.close();
		db.close();
		return list;
	}

	public long updateStatusCheck(Integer cod) {
		Log.e("fail", "entrou no update");
		SQLiteDatabase db;
		db = this.getWritableDatabase();

		ContentValues valores = new ContentValues();

		valores.put(STATUS_PREENCHIMENTO, 3);

		// insert row
		String where = COD + " =  ?";
		String[] clause = new String[1];
		clause[0] = cod.toString();

		long respCod = db.update(T_CHECK_LISTS, valores, where, clause);
		db.close();
		System.out.println(respCod);
		return respCod;
	}

	public Usuarios getUsuario(String email, String senha) {
		Usuarios user = new Usuarios();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM "
				+ T_USUARIOS + " WHERE " + LOGIN
				+ " = \"" + email + "\" AND " + SENHA + " = \"" + senha + "\";";


		Log.e("query", selectQuery);
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			while (!c.isAfterLast()) {
				if (c.getString(c.getColumnIndex(COD_DARWIN)) != null) {
					user.setCod(c.getString(c.getColumnIndex(COD_DARWIN)));
					user.setLogin(c.getString(c.getColumnIndex(LOGIN)));
					user.setSenha(c.getString(c.getColumnIndex(SENHA)));
					user.setCaminho_logo(c.getString(c.getColumnIndex(CAMINHO_LOGO)));
					user.setCod_cliente(c.getInt(c.getColumnIndex(COD_CLIENTE)));
					user.setRazao_social(c.getString(c.getColumnIndex(RAZAO_SOCIAL)));
					user.setTipoUsuario(c.getInt(c.getColumnIndex(TIPO_USUARIO)));
					System.out.println(user.toString());
					c.moveToNext();
				}
			}
		}
		c.close();
		db.close();
		System.out.println(user.toString());
		return user;
	}
}
