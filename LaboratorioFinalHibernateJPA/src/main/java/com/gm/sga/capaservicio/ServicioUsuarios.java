package com.gm.sga.capaservicio;

import com.gm.sga.capadatos.dao.UsuarioDAO;
import com.gm.sga.capadatos.dto.Usuario;
import java.util.List;

public class ServicioUsuarios {
    // El Dao se asocia con la clase

    UsuarioDAO usuarioDao = new UsuarioDAO();

    // Esta clase se comunica con los DAO's que necesite
    public List<Usuario> listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public boolean guardarUsuario(Usuario usuario) {
        if (usuario != null && usuario.getIdUsuario() == null) {
            //insert
            usuarioDao.insertar(usuario);
        } else {
            //update
            usuarioDao.actualizar(usuario);
        }

        return true;// si nada fallo, regresamos verdadero
    }

    public boolean eliminarUsuario(Integer idUsuario) {
        //delete
        usuarioDao.eliminar(new Usuario(idUsuario));
        return true;// si nada falla regresamos verdadero
    }

    public Usuario encontrarUsuario(Integer idUsuario) {
        return usuarioDao.buscarPorId(new Usuario(idUsuario));
    }

    public boolean usuarioExistente(Usuario usuarioDTO) {
        List usuarios = this.usuarioDao.findByExample(usuarioDTO);
        //Si es mayor que cero existe el usuario
        return usuarios.size() > 0;

    }

}