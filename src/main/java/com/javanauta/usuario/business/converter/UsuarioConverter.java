package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.dto.EnderecoDTO;
import com.javanauta.usuario.business.dto.TelefoneDTO;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UsuarioConverter {

    /*Converte DTOS em Entidades*/

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaDeEnderecos(usuarioDTO.getEnderecos()))
                .telefones(paraListaDeTelefones(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaDeEnderecos(List<EnderecoDTO> enderecoDTOS){
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoDTO enderecoDTO : enderecoDTOS){
            enderecos.add(paraEndereco(enderecoDTO));
        }
        return enderecos;
    }

    public List<Telefone> paraListaDeTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    /* De Entidade Para DTOS*/

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaDeEnderecosDTO(usuario.getEnderecos()))
                .telefones(paraListaDeTelefonesDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaDeEnderecosDTO(List<Endereco> enderecos){
        List<EnderecoDTO> enderecosDTOS = new ArrayList<>();
        for(Endereco endereco : enderecos){
            enderecosDTOS.add(paraEnderecoDTO(endereco));
        }
        return enderecosDTOS;
    }

    public List<TelefoneDTO> paraListaDeTelefonesDTO(List<Telefone> telefone){
        return telefone.stream().map(this::paraTelefoneDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    //Metodo que mescla dados novos com dados ja existentes no banco de dados
    //Averigua se foi passado um novo dado, se nao foi, mantem o dado existente no banco de dados
    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .id(entity.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity){
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : enderecoEntity.getComplemento())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : enderecoEntity.getCidade())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : enderecoEntity.getEstado())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : enderecoEntity.getCep())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity){
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefoneEntity.getNumero())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .build();
    }
}
