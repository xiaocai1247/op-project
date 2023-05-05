import request from '@/utils/request'

const api_name = '/admin/system/sysRole'
export default {
    //角色列表-条件分页查询
    getPageList(current, limit, searchObj) {
        return request({
            url: `${api_name}/${current}/${limit}`,
            method: 'get',
            //如果是普通的对象参数写法 params:对象参数名称
            //如果是json格式写法 data:对象参数名称
            params: searchObj,
        })
    },
    //角色删除
    removeById(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete',
        })
    },
    //角色添加
    saveRole(role) {
        return request({
            url: `${api_name}/save`,
            method: 'post',
            data: role,
        })
    },
    //根据id查询
    getById(id) {
        return request({
            url: `${api_name}/get/${id}`,
            method: 'get'
        })
    },
    //修改
    updateById(role) {
        return request({
            url: `${api_name}/update`,
            method: 'put',
            data: role
        })
    },
    //批量删除
    batchRemove(idList) {
        return request({
            url: `${api_name}/batchRemove`,
            method: 'delete',
            data: idList
        })
    },
    getRoles(adminId) {
        return request({
          url: `${api_name}/toAssign/${adminId}`,
          method: 'get'
        })
      },
      
    assignRoles(assginRoleVo) {
    return request({
        url: `${api_name}/doAssign`,
        method: 'post',
        data: assginRoleVo
    })
    }
}