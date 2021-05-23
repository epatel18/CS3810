// Referenced Activity 12 and Hibernate resource

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="agencies")
public class Agency {

    @Id
    private int ouid;
    private String name;


    @JoinColumn(name="ouid")
    @OneToMany(targetEntity = Agent.class)
    private List<Agent> agents;

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", ouid=" + ouid +
                ", agents=" + agents +
               '}';
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOuid() {
        return ouid;
    }

    public void setOuid(int ouid) {
        this.ouid = ouid;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents, EntityManager em) {
        em.getTransaction().begin();
        for (Agent agent : agents){
            agent.setOuid(ouid);
            em.persist(agent);
        }
        em.getTransaction().commit();

        this.agents = agents;
    }
}