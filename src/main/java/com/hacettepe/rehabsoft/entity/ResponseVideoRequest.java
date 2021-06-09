package com.hacettepe.rehabsoft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "response_video_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "response_video_request_seq", initialValue = 1, allocationSize = 1)
public class ResponseVideoRequest extends BaseEntity{

    @Column(name = "response_content")
    private String responseContent;

    @OneToOne
    @JoinColumn(name = "video_request_id")
    private VideoRequest videoRequest;

    @OneToMany(mappedBy = "responseVideoRequest")
    private Collection<RequestedVideo> requestedVideoCollection;

}
