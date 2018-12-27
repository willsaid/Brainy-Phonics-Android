package com.hearatale.phonics.ui.sight_word;

import com.hearatale.phonics.data.model.phonics.SightWordModel;
import com.hearatale.phonics.data.model.typedef.SightWordsCategoryDef;

import java.util.List;

public interface ISightWordPresenter {
    List<SightWordModel> getSightWords(@SightWordsCategoryDef int category);
}
