package com.example.myapplicationfromtutorial;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplicationfromtutorial.model.CharacterOnBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OtherThingFragment extends Fragment {

    private OtherThingViewModel mViewModel;

    public static OtherThingFragment newInstance() {
        return new OtherThingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.other_thing_fragment, container, false);
        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintLayoutOther);
        if (constraintLayout.getViewById(1) == null) {
            ArrayList<CharacterOnBoard> characterOnBoardList = this.getArguments().getParcelableArrayList("characterOnBoardList");

            List<String> characterListImageUrl = characterOnBoardList.stream()
                    .map(CharacterOnBoard::getImgUrl)
                    .collect(Collectors.toList());
            ArrayList<String> characterArrayListImageUrl = new ArrayList<>(characterListImageUrl);

            createImageViewForEachCharacter(characterArrayListImageUrl, constraintLayout);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(OtherThingViewModel.class);
        // TODO: Use the ViewModel
    }


    private void createImageViewForEachCharacter(ArrayList<String> imageUrlCharacterList, ConstraintLayout constraintLayout) {
        int imageIds[] = new int[imageUrlCharacterList.size()];

        for(int i = 0; i < imageUrlCharacterList.size(); i++) {
            int drawableId = getResources().getIdentifier(
                    imageUrlCharacterList.get(i),
                    "drawable",
                    "com.example.myapplicationfromtutorial"
            );

            ImageView imageView = createCharacterImageView(drawableId, i);

            imageIds[i] = imageView.getId();
            constraintLayout.addView(imageView);
        };

        addConstraintsToImageViews(constraintLayout, imageIds, imageUrlCharacterList.size());
    }

    private ImageView createCharacterImageView(int characterDrawableImage, int index) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
        ImageView imageView = new ImageView(getActivity());
        imageView.setId(index);
        imageView.setImageDrawable(getResources().getDrawable(characterDrawableImage));
        imageView.setLayoutParams(layoutParams);
        imageView.setRight(50);
        imageView.setLeft(60);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCharacterImageShown = imageView.getDrawable().getConstantState().equals
                        (imageView.getResources().getDrawable(characterDrawableImage).getConstantState());
                imageView.setImageDrawable(getResources().getDrawable(
                        isCharacterImageShown
                                ? R.drawable.boursin // when card is hidden always show the same
                                : characterDrawableImage
                ));
            }
        });
        return imageView;
    }

    private void addConstraintsToImageViews(ConstraintLayout constraintLayout, int[] imageIds, int imageUrlCharacterListSize) {
        for(int i = 0; i < imageUrlCharacterListSize - 1; i++) {
            ConstraintSet constraints = new ConstraintSet();
            constraints.clone(constraintLayout);
            constraints.connect(imageIds[i], ConstraintSet.START, imageIds[i+1], ConstraintSet.END, 15);
            constraints.applyTo(constraintLayout);
        }
    }

}
