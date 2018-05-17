package com.estone.erp.productdev.wish.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.io.Serializable;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.estone.erp.productdev.wish.command.WishProductUpdateCommand;
import com.estone.erp.productdev.wish.event.WishProductUpdateEvent;
import com.estone.erp.productdev.wish.mongo.model.bo.WishProduct;

/***
 * wish产品
 */
@Aggregate
public class WishProductAggregate implements Serializable {
    private static final long serialVersionUID = -110012795787201323L;

    @AggregateIdentifier
    private String identifier;
    
    private String id;

    private String pid;

    private String pname;
    
    public WishProductAggregate() {
        
    }
    
    @CommandHandler
    public WishProductAggregate(WishProductUpdateCommand command) {
        this.identifier = command.getIdentifier();
        apply(new WishProductUpdateEvent(command));
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}
