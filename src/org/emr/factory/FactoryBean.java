/**
 *
 */
package org.emr.factory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.emr.contant.ApplicationConstant;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nishith Shah
 */
public abstract class FactoryBean implements Serializable{

    private Long id;

    public abstract Long getId();

    public abstract void setId(Long id);

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor p : propertyDescriptors) {
                System.out.println(p.getName() + "'s Superclass :: " + p.getPropertyType().getSuperclass());
                if (ApplicationConstant.TYPE_PRIMARY_LIST.contains(p.getPropertyType().getSimpleName().toLowerCase())) {
                    json.put(p.getName(), p.getReadMethod().invoke(this));
                } else if (p.getPropertyType().getSuperclass() != null && p.getPropertyType().getSuperclass().getSimpleName().toLowerCase().equals("factorybean")) {
                    json.put(p.getName(), p.getReadMethod().invoke(this).getClass().getMethod("toJSON").invoke(p.getReadMethod().invoke(this)));
                } else if (p.getPropertyType().isArray()) {
                    json.put(p.getName(), getJSONArrayFromArray(p.getReadMethod().invoke(this)));
                } else if (p.getReadMethod().invoke(this) instanceof Set<?>) {
                    json.put(p.getName(), getJSONFromSet(p.getReadMethod().invoke(this)));
                } else if (p.getReadMethod().invoke(this) instanceof List<?>) {
                    json.put(p.getName(), getJSONFromList(p.getReadMethod().invoke(this)));
                } else if (p.getReadMethod().invoke(this) instanceof Map<?, ?>) {
                    json.put(p.getName(), getJSONFromMap(p.getReadMethod().invoke(this)));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    private JSONArray getJSONArrayFromArray(Object obj) {
        JSONArray array = new JSONArray();
        try {
            if (obj != null) {
                int size = Array.getLength(obj);
                System.out.println(size);
                for (int i = 0; i < size; i++) {
                    Object o = Array.get(obj, i);
                    if (ApplicationConstant.TYPE_PRIMARY_LIST.contains(o.getClass().getSimpleName().toLowerCase())) {
                        array.put(o);
                    } else if (o.getClass().getSuperclass().getSimpleName().toLowerCase().equals("factorybean")) {
                        array.put(((FactoryBean) o).toJSON());
                    } else if (o.getClass().isArray()) {
                        array.put(getJSONArrayFromArray(o));
                    } else if (o instanceof Set<?>) {
                        array.put(getJSONFromSet(o));
                    } else if (o instanceof List<?>) {
                        array.put(getJSONFromList(o));
                    } else if (o instanceof Map<?, ?>) {
                        array.put(getJSONFromMap(obj));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    private JSONArray getJSONFromSet(Object obj) {
        JSONArray array = new JSONArray();
        try {
            if (obj != null) {
                Set<?> set = (Set<?>) obj;
                Iterator<?> itr = set.iterator();
                while (itr.hasNext()) {
                    Object o = itr.next();
                    System.out.println("" + (o instanceof Set<?>));
                    if (ApplicationConstant.TYPE_PRIMARY_LIST.contains(o.getClass().getSimpleName().toLowerCase())) {
                        array.put(o);
                    } else if (o.getClass().getSuperclass().getSimpleName().toLowerCase().equals("factorybean")) {
                        array.put(((FactoryBean) o).toJSON());
                    } else if (o.getClass().isArray()) {
                        array.put(getJSONArrayFromArray(o));
                    } else if (o instanceof Set<?>) {
                        array.put(getJSONFromSet(o));
                    } else if (o instanceof List<?>) {
                        array.put(getJSONFromList(o));
                    } else if (o instanceof Map<?, ?>) {
                        array.put(getJSONFromMap(obj));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    private JSONObject getJSONFromList(Object obj) {
        JSONObject json = new JSONObject();
        try {
            if (obj != null) {
                List<?> list = (List<?>) obj;
                Iterator<?> itr = list.iterator();
                for (Object o : list) {
                    if (ApplicationConstant.TYPE_PRIMARY_LIST.contains(o.getClass().getSimpleName().toLowerCase())) {
                        json.put(list.indexOf(o) + "", o);
                    } else if (o.getClass().getSuperclass().getSimpleName().toLowerCase().equals("factorybean")) {
                        json.put(list.indexOf(o) + "", ((FactoryBean) o).toJSON());
                    } else if (o.getClass().isArray()) {
                        json.put(list.indexOf(o) + "", getJSONArrayFromArray(o));
                    } else if (o instanceof Set<?>) {
                        json.put(list.indexOf(o) + "", getJSONFromSet(o));
                    } else if (o instanceof List<?>) {
                        json.put(list.indexOf(o) + "", getJSONFromList(o));
                    } else if (o instanceof Map<?, ?>) {
                        json.put(list.indexOf(o) + "", getJSONFromMap(obj));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }

    private JSONObject getJSONFromMap(Object obj) {
        JSONObject json = new JSONObject();
        try {
            if (obj != null) {
                Map<?, ?> map = (Map<?, ?>) obj;
                Set<?> keySet = map.keySet();
                Iterator<?> itr = keySet.iterator();
                while (itr.hasNext()) {
                    Object key = itr.next();
                    Object o = map.get(key);
                    if (ApplicationConstant.TYPE_PRIMARY_LIST.contains(o.getClass().getSimpleName().toLowerCase())) {
                        json.put(key.toString(), o);
                    } else if (o.getClass().getSuperclass().getSimpleName().toLowerCase().equals("factorybean")) {
                        json.put(key.toString(), ((FactoryBean) o).toJSON());
                    } else if (o.getClass().isArray()) {
                        json.put(key.toString(), getJSONArrayFromArray(o));
                    } else if (o instanceof Set<?>) {
                        json.put(key.toString(), getJSONFromSet(o));
                    } else if (o instanceof List<?>) {
                        json.put(key.toString(), getJSONFromList(o));
                    } else if (o instanceof Map<?, ?>) {
                        json.put(key.toString(), getJSONFromMap(obj));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FactoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
